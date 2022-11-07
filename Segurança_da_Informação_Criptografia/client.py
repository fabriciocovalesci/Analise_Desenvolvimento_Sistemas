import tftpy
import sys
import os



client = tftpy.TftpClient('127.0.0.1', 69)


def download_file(filename, file):
    return client.download(filename, os.getcwd() + '/' + file, timeout=10)

def upload_file(filename, file):
    return client.upload(filename, os.getcwd() + '/' + file, timeout=10)


def main():
    if len(sys.argv) < 3:
        print('Error: formato python3 client.py -d filename.txt new_file.txt | python3 client.py -u filename.txt new_file.txt')
    
    if sys.argv[1] in ('-d', 'download'):
        upload_file(sys.argv[2], sys.argv[3])

    if sys.argv[1] in ('-u', 'upload'):
        download_file(sys.argv[2], sys.argv[3])

    else:
        print(""" 
        Helper: comand download > python client.py -d text.txt new_text.txt
        Helper: comand upload > python client.py -u text.txt new_text.txt
        """)


if __name__ == '__main__':
    main()
