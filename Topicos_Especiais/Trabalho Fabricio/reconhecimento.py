import face_recognition
import cv2
import numpy as np
import serial
import Envia_dados
import Not_Envia
import Desliga

# Esta é uma demonstração de reconhecimento facial em execução em vídeo ao vivo da sua webcam. É um pouco mais complicado que o
# outro exemplo, mas inclui alguns ajustes básicos de desempenho para tornar as coisas muito mais rápidas:
# 1. Processar cada quadro de vídeo em 1/4 de resolução (embora ainda exiba em resolução total)
# 2. Somente detecte rostos em todos os outros quadros de vídeo.

# ATENÇÃO: Este exemplo requer que o OpenCV (a biblioteca `cv2`) seja instalado apenas para ler a sua webcam.
# OpenCV * não * é necessário para usar a biblioteca face_recognition. Só é necessário se você quiser executar este
# demonstração específica. Se você tiver problemas para instalá-lo, tente qualquer uma das outras demos que não precisem dele.

# Obtenha uma referência para a webcam # 0 (a padrão)
video_capture = cv2.VideoCapture(0)

# Carregue uma foto de amostra e saiba como reconhecê-la.
minha_foto = face_recognition.load_image_file("Minha_imagem.png")
obama_face_encoding = face_recognition.face_encodings(minha_foto)[0]

# Carregue uma segunda imagem de amostra e saiba como reconhecê-la.
biden_image = face_recognition.load_image_file("2.jpeg")
biden_face_encoding = face_recognition.face_encodings(biden_image)[0]

# Crie matrizes de codificações de faces conhecidas e seus nomes
known_face_encodings = [
    obama_face_encoding,
    biden_face_encoding
]
known_face_names = [
    "Fabricio"
]

# Inicialize algumas variáveis
face_locations = []
face_encodings = []
face_names = []
process_this_frame = True

while True:
    # Pegue um único quadro de vídeo
    ret, frame = video_capture.read()

    # Redimensionar quadro de vídeo para 1/4 de tamanho para processamento de reconhecimento de rosto mais rápido
    small_frame = cv2.resize(frame, (0, 0), fx=0.25, fy=0.25)

    # Converta a imagem da cor BGR (que o OpenCV usa) para a cor RGB (que utiliza o face_reconhecimento)
    rgb_small_frame = small_frame[:, :, ::-1]

    #  Apenas processe todos os outros quadros de vídeo para economizar tempo
    if process_this_frame:
        # Encontre todos os rostos e codificações de rosto no quadro atual do vídeo
        face_locations = face_recognition.face_locations(rgb_small_frame)
        face_encodings = face_recognition.face_encodings(rgb_small_frame, face_locations)

        face_names = []
        nomes = 'Desconhecido'
        Names = ''
        name = ''
        for face_encoding in face_encodings:
            # Veja se o rosto é compatível com o (s) rosto (s) conhecido (s)
            matches = face_recognition.compare_faces(known_face_encodings, face_encoding)
            name = 'Desconhecido'
            Names = 'Desconhecido'


            # Se uma correspondência foi encontrada em known_face_encodings, use apenas a primeira.
            if True in matches:
                first_match_index = matches.index(True)
                name = known_face_names[first_match_index]
            face_names.append(name)
        Eu = 'Fabricio'
        if Eu in face_names:
            Envia_dados.rele_2()

        elif nomes == Names:
            Not_Envia.rele_1()


    process_this_frame = not process_this_frame

    # Exibir os resultados
    for (top, right, bottom, left), name in zip(face_locations, face_names):
        # Escale os locais das faces de backup, pois o quadro que detectamos foi dimensionado para 1/4 do tamanho
        top *= 4
        right *= 4
        bottom *= 4
        left *= 4

        # Desenhe uma caixa ao redor do rosto
        cv2.rectangle(frame, (left, top), (right, bottom), (0, 0, 255), 2)

        # Desenhe um rótulo com um nome abaixo do rosto
        cv2.rectangle(frame, (left, bottom - 35), (right, bottom), (0, 0, 255), cv2.FILLED)
        font = cv2.FONT_HERSHEY_DUPLEX
        cv2.putText(frame, name, (left + 6, bottom - 6), font, 1.0, (255, 255, 255), 1)

    # Exibe a imagem resultante
    cv2.imshow('Video', frame)


    # Clique 'q' no teclado para sair!
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

# Solte o manípulo na webcam
video_capture.release()
cv2.destroyAllWindows()
