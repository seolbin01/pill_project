import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileReader;

import DataPackage.Data;
                                            
public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket server = new ServerSocket();
        SocketAddress addr = new InetSocketAddress("172.20.10.2", 5000);
        server.bind(addr);

        ProcessBuilder builder, builder2, builder3;
        Process process, process2;
        BufferedReader br, br2;
        String line, line2, id;
        Data data, data2;
        int idx = 0, num = 0;


        while(true){
            System.out.println("Listening . . . ");

            Socket client = server.accept();
            System.out.println("Accept . . ");

            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            Data fromClient = (Data)in.readObject(); //받은 데이터 읽는다

            
            // =======================================================================================================================================================================
            
            System.out.println("[Data From Client]");
            Files.write(Path.of("C:\\Users\\82103\\Desktop\\project\\dir_camera_image_org\\pill_copy_1.png"), fromClient.getImg());
            //받은 이미지 dir_camera_image_org폴더에 저장

            // =======================================================================================================================================================================
            
            builder = new ProcessBuilder("cmd.exe", "/C", "conda", "activate", "pill", "&&", "^cd", 
            "C:\\Users\\82103\\Desktop\\project","&&", "^python", ".\\proj_pill\\labeling.py");
            // 파이썬 파일 실행 cmd 역할ip
            //가상환경 pill 연결 및 레이블링코드 실행
            
            process2 = builder.start();
            br2 = new BufferedReader(new InputStreamReader(process2.getInputStream(), "euc-kr")); //프로세스에 있는 데이터를 읽을 수 있게끔 형변환
            if ((line2 = br2.readLine()) != null) num = Integer.parseInt(line2);
            //자른 알약 이미지 개수 정보 끌어오기
            //System.out.println(num);


            switch(num){   //알약 이미지 개수에 따라 case 분리
                case 1:
                    //System.out.println(num);
                    builder = new ProcessBuilder("cmd.exe", "/C", "conda", "activate", "pill", "&&", "^cd", 
                    "C:\\Users\\82103\\Desktop\\project","&&", "^python", ".\\proj_pill\\camera_proj.py");
                    // 파이썬 파일 실행 cmd 역할ip
                    process = builder.start();  //builder 대한 참조, 실행
                    br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr")); //프로세스에 있는 데이터를 읽을 수 있게끔 형변환
                    //br = new BufferedReader(new FileReader("C:\\Users\\82103\\Desktop\\project\\dir_pill_info\\pill_info.txt"));

                    data = new Data();
                    data.init();
                    id = "";
                    idx = 0;
                    while ((line = br.readLine()) != null) {
                        //System.out.println(line);
                        //System.out.println(idx);
                        //if(line.contains("Class:")) idx++;
                        
                        switch(idx){
                            case 0:
                                idx++;
                                continue;
                            case 1:
                                idx++;
                                continue;
                            case 2:
                                idx++;
                                continue;
                            case 3:
                                data.setName(line);   //게보린정 300mg/PTP
                                //System.out.println(line);
                                idx++;
                                break;
                            case 4:
                                data.setCompany(line);   //삼진제약(주)
                                idx++;
                                break;
                            case 5:
                                data.setClass_no(line);   //[01140]해열.진통.소염제
                                idx++;
                                break;
                            case 6:
                                data.setOtc_code(line);   //일반의약품
                                idx++;
                                break;
                            case 7:
                                data.setChart(line);   //홍색의 정제
                                idx++;
                                break;               
                            case 8:
                                data.setPrint_front(line);   //GR
                                idx++;
                                break;
                            case 9:
                                data.setPrint_back(line);   //
                                idx++;
                                break;        
                            case 10:         
                                data.setUrl(line);    //http://connectdi.com/design/img/drug/1N5PLuAiUvi.jpg
                                idx++;
                                break;
                            case 11:
                                idx++;
                                break;
                        }

                        
                    }
                
                    //========================================================================================================================
                    
                    out.writeObject(data); 
                    System.out.println("data sending complete . . .");
                    System.out.println("==============================");
                    client.close();
                    br.close();

                    builder2 = new ProcessBuilder("cmd.exe", "/C", "del", "/q", "C:\\Users\\82103\\Desktop\\project\\dir_camera_image\\after_1.png");            
                    builder2.start();
                    break;

        

                case 2:
                    builder = new ProcessBuilder("cmd.exe", "/C", "conda", "activate", "pill", "&&", "^cd", 
                    "C:\\Users\\82103\\Desktop\\project","&&", "^python", ".\\proj_pill\\camera_proj.py");
                    // 파이썬 파일 실행 cmd 역할ip
                    process = builder.start();  //builder 대한 참조, 실행
                    br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr")); //프로세스에 있는 데이터를 읽을 수 있게끔 형변환
                    //br = new BufferedReader(new FileReader("C:\\Users\\82103\\Desktop\\project\\dir_pill_info\\pill_info.txt"));
                        
                    data = new Data();
                    data.init();
                    id = "";
                    idx = 0;
                    while ((line = br.readLine()) != null) {
                        //System.out.println(line);
                        if(line.contains("Class:")) idx++;

                        switch(idx){
                            case 0:
                                idx++;
                                continue;
                            case 1:
                                idx++;
                                continue;
                            case 2:
                                idx++;
                                continue;
                            case 3:
                                idx++;
                                continue;
                            case 4:
                                idx++;
                                continue;
                            case 5:
                                idx++;
                                continue;
                            case 6:
                                data.setName(line);   //타이레놀정500mg
                                idx++;
                                break;
                            case 7:
                                data.setCompany(line);   //(주)한국얀센
                                idx++;
                                break;
                            case 8:
                                data.setClass_no(line);   //[01140]해열.진통.소염제
                                idx++;
                                break;
                            case 9:
                                data.setOtc_code(line);   //일반의약품
                                idx++;
                                break;
                            case 10:
                                data.setChart(line);   //백색의 장방형 필름코팅정제
                                idx++;
                                break;               
                            case 11:
                                data.setPrint_front(line);   //TYLENOL
                                idx++;
                                break;
                            case 12:
                                data.setPrint_back(line);   //500
                                idx++;
                                break;        
                            case 13:         
                                data.setUrl(line);    //http://connectdi.com/design/img/drug/147427630838400132.jpg
                                idx++;
                                break;
                            



                            // case 9:
                            //     idx++;
                            //     continue;
                            case 14:
                                data.setName2(line);   //타이레놀정 160mg
                                idx++;
                                break;
                            case 15:
                                data.setCompany2(line);   //(주)한국얀센
                                idx++;
                                break;
                            case 16:
                                data.setClass_no2(line);   //[01140]해열.진통.소염제
                                idx++;
                                break;
                            case 17:
                                data.setOtc_code2(line);   //일반의약품
                                idx++;
                                break;
                            case 18:
                                data.setChart2(line);   //백색의 장방형 정제
                                idx++;
                                break;               
                            case 19:
                                data.setPrint_front2(line);   //TY-160
                                idx++;
                                break;
                            case 20:
                                data.setPrint_back2(line);   //분할선
                                idx++;
                                break;        
                            case 21:         
                                data.setUrl2(line);    //http://connectdi.com/design/img/drug/154609810414600099.jpg
                                idx++;
                                break;
                            case 22:
                                idx++;
                                break;
                        }

                        
                    }
            
                //========================================================================================================================
                //이 부분은 switch문 밖으로 빼도 될듯?? 근데 case2도 완성하고 난 다음에 생각해보자
                out.writeObject(data);
                System.out.println("data sending complete . . .");
                System.out.println("==============================");
                client.close();
                br.close();

                builder2 = new ProcessBuilder("cmd.exe", "/C", "del", "/q", "C:\\Users\\82103\\Desktop\\project\\dir_camera_image\\after_1.png" );            
                builder2.start();
                break;

    

                            
            }
            
        }

    }
}