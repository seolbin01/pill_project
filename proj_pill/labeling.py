import cv2

img0 = cv2.imread('./dir_camera_image_org/pill_copy_1.png', cv2.IMREAD_UNCHANGED)   
src = cv2.imread('./dir_camera_image_org/pill_copy_1.png', cv2.IMREAD_GRAYSCALE)

h, w, c = img0.shape

if w < 500:   #혹시 작은 이미지가 오면 사이즈 늘리기
    img0 = cv2.resize(src , (700, 933))
    src = cv2.resize(src , (700, 933))

src = cv2.GaussianBlur(src, (3,3), 2)  # 노이즈 제거
_, src_bin = cv2.threshold(src, 0, 255, cv2.THRESH_OTSU) 

cnt, labels, stats, centroids = cv2.connectedComponentsWithStats(src_bin)

dst = cv2.cvtColor(src, cv2.COLOR_GRAY2BGR)

"""
#레이블링 잘 됐는지 확인할 때 쓰는 코드
for i in range(1, cnt):
    x, y, w, h, area = stats[i]
    if w > dst.shape[1] * 0.1 and w < dst.shape[1] * 0.8:  #이미지 넓이 10%~50% 넓이를 가진 객체만을 대상으로
        #레이블링한 객체에 직사각형 그리기
        cv2.rectangle(dst, (x, y), (x+w, y+h), (0, 255, 255))

cv2.imshow('src', src)
cv2.imshow('src_bin', src_bin)
cv2.imshow('dst', dst)
cv2.waitKey()
cv2.destroyAllWindows()
"""


test = []

for i in range(1, cnt):
    x, y, w, h, area = stats[i]
    if w > dst.shape[1] * 0.1 and w < dst.shape[1] * 0.8: #이미지 10%~80%? 넓이를 가진 객체만을 대상으로
        # 이미지를 자른다.
        test.append(img0[y-100:y+h+100, x-100:x+w+100].copy())

#dir = 'dir_camera_image/'
dir = 'C:\\Users\\82103\\Desktop\\project\\dir_camera_image\\'

num = 0 

for img in test:
    new_image_file = dir + 'after_' + str(num) + '.png'
    num = num+1
    cv2.imwrite(new_image_file, img)


#폴더에 저장한 데이터가 몇개인지 출력하는 거 추가 + server.java에서 읽고 갯수 확인
print(len(test))

