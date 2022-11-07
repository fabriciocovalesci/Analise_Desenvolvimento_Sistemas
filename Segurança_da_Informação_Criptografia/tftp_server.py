import socket, binascii, struct
import time

class tftp:

    RRQ, WRQ, DATA, ACK, ERROR, OACK = range(1,7)

    ACK_STRUCT = '! H H'
    DATA_STRUCT = '! H H'

    def __init__(self,packet):
        self.opcode = struct.unpack('!H', packet[:2])[0]
        if self.opcode == self.RRQ or self.opcode == self.WRQ:
            split = packet[2:-1].split(b'\x00')
            self.filename = split[0]
            self.mode = split[1]
        elif self.opcode == self.DATA:
            self.block = struct.unpack('!H', packet[2:4])[0]
            self.data = packet[4:]
        elif self.opcode == self.ACK:
            self.block = struct.unpack('!H', packet[2:4])[0]
        elif self.opcode == self.ERROR:
            self.errorcode = struct.unpack('!H', packet[2:4])[0]
            self.errmsg = packet[4:-1]

    def get_opcode(self):
        return self.opcode

    def get_block(self):
        return self.block

    def get_data(self):
        return self.data

    def get_filename(self):
        return self.filename

    def send_chunk_packet(self, block, chunk):
        values = (self.DATA, block)
        return struct.pack(self.DATA_STRUCT, *values) + chunk

    def send_ack_packet(self, block):
        values = (self.ACK, block)
        return struct.pack(self.ACK_STRUCT, *values)

class tftp_server:

    def __init__(self, host='', port=69):
        self.udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.udp.bind((host, port))

    def start_server(self):
        print('server listener ', socket.gethostname())
        buff = b''

        while True:
            time.sleep(1000)
            print("olaaa 3")
            msg, cliente = self.udp.recvfrom(1024)
            print(msg)
            tftp_packet = tftp(msg)
            if tftp_packet.get_opcode() == tftp_packet.WRQ:
                filename = tftp_packet.get_filename()
                self.udp.sendto(tftp_packet.send_ack_packet(0),cliente)
            elif tftp_packet.get_opcode() == tftp_packet.RRQ:
                with open(tftp_packet.get_filename(), 'rb') as file:
                    data = file.read()
                    chunks = [data[i:i+512] for i in range(0, len(data), 512)]
                    for num, chunk in enumerate(chunks):
                        self.udp.sendto(tftp_packet.send_chunk_packet(num+1, chunk),cliente)
                        msg, cliente = self.udp.recvfrom(1024)

            elif tftp_packet.get_opcode() == tftp_packet.DATA:
                buff += tftp_packet.get_data()
                if len(tftp_packet.get_data()) != 512:
                    with open(filename, 'wb+') as file:
                        file.write(buff)
                    buff = b''
                self.udp.sendto(tftp_packet.send_ack_packet(tftp_packet.get_block()),cliente)

        self.udp.close()

def main():
    server = tftp_server()
    server.start_server()

if __name__== "__main__":
    main()
