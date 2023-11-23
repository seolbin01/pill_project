from keras.models import load_model
from PIL import Image, ImageOps #Install pillow instead of PIL
import numpy as np
import os
import json


dir_camera_image = []
dir = 'C:\\Users\\82103\\Desktop\\project\\dir_camera_image'

#파일 내에 있는 이미지 불러오기
os.chdir(dir)
file_name =os.listdir()
for i in file_name:
    if os.path.splitext(i)[1]=='.png':   #파일 확장자가 png이면
        #dir_camera_image=i   #변수에 저장
        dir_camera_image.append(i)  #리스트에 저장



# Load the model
model = load_model('C:\\Users\\82103\\Desktop\\project\\converted_keras\\keras_model.h5', compile=False)
#model = load_model('keras_Model.h5', compile=False)

# Load the labels
class_names = open('C:\\Users\\82103\\Desktop\\project\\converted_keras\\labels.txt', 'r',encoding='UTF-8').readlines()
#===================================================================================

pill_num = 1
res = []

for pill_img in dir_camera_image:

    # 출력의 정밀도 설정, 표기 단순화
    np.set_printoptions(suppress=True) 

    #  배열에 넣을 수 있는 수와 이미지는 튜플의 첫 번째 위치, 이 경우 1에 의해 결정된다.
    data = np.ndarray(shape=(1, 224, 224, 3), dtype=np.float32)

    # Replace this with the path to your image / 촬영한 이미지 넣기
    image = Image.open(os.path.join(dir, pill_img)).convert('RGB')

    #이미지를 224*224로 조정한다음 중앙에서 자른다
    size = (224, 224)
    image = ImageOps.fit(image, size, Image.Resampling.LANCZOS)



    #이미지를 넘파이 배열로 바꿈
    image_array = np.asarray(image)

    # 값의 범위(scale)를 0~1 사이의 값으로 바꾸는 것. 정규화. 데이터 프레임 타입 바꾸기
    normalized_image_array = (image_array.astype(np.float32) / 127.0) - 1

    # 이미지를 배열에 로드합니다.
    data[0] = normalized_image_array

    # run the inference
    prediction = model.predict(data) #예측함수
    index = np.argmax(prediction) #가장 큰 원소의 인덱스를 반환(예측퍼센트 가장 큰), 가장 큰 원소가 여러개(중복) 있는 경우 가장 앞의 인덱스를 반환
    class_name = class_names[index] #가장 큰 원소의 인덱스
    confidence_score = prediction[0][index]

    #print('Class:', class_name, end='')
    #print('Confidence score:', confidence_score)

    #json 파일 참조하기

    if class_name=='0 게보린정 300mg/PTP\n':
        os.chdir("C:\\Users\\82103\\Desktop\\project\\pill_data\\pill0")
        file_path=os.listdir()[0]
        with open(file_path,'r',encoding='UTF8') as f: 
            json_file = json.load(f)
            json_data = json_file['images']

        if json_data[0]['dl_name']:
            res.append([json_data[0]['dl_name'] , json_data[0]['dl_company'],json_data[0]['di_class_no'],json_data[0]['di_etc_otc_code'],json_data[0]['chart'],json_data[0]['print_front'],json_data[0]['print_back'],json_data[0]['img_key']])
        
    elif class_name=='1 타이레놀정 160mg\n':
        os.chdir("C:\\Users\\82103\\Desktop\\project\\pill_data\\pill1")
        file_path=os.listdir()[0]
        with open(file_path,'r',encoding='UTF8') as f: 
            json_file = json.load(f)
            json_data = json_file['images']

        if json_data[0]['dl_name']:
            res.append([json_data[0]['dl_name'] , json_data[0]['dl_company'],json_data[0]['di_class_no'],json_data[0]['di_etc_otc_code'],json_data[0]['chart'],json_data[0]['print_front'],json_data[0]['print_back'],json_data[0]['img_key']])

    elif class_name=='2 타이레놀정 500mg\n':
        os.chdir("C:\\Users\\82103\\Desktop\\project\\pill_data\\pill2")
        file_path=os.listdir()[0]
        with open(file_path,'r',encoding='UTF8') as f: 
            json_file = json.load(f)
            json_data = json_file['images']

        if json_data[0]['dl_name']:
            res.append([json_data[0]['dl_name'] , json_data[0]['dl_company'],json_data[0]['di_class_no'],json_data[0]['di_etc_otc_code'],json_data[0]['chart'],json_data[0]['print_front'],json_data[0]['print_back'],json_data[0]['img_key']])

    #json 정보 출력하기
    #for line in res:
    #    print(line)
    
    pill_num = pill_num+1
    #print(pill_num)

    #print(res)

for pill in res:
    for line in pill:
        print(line)


"""
with open('C:\\Users\\82103\\Desktop\\project\\dir_pill_info\\pill_info.txt','w',encoding='UTF-8') as f:
    for pill in res:
        for line in pill:
            f.write(line+'\n')
"""