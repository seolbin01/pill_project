package DataPackage;
import java.io.Serializable;

public class Data implements Serializable {

    private int flag, flag2;
    private String dl_name, dl_name2;
    private String dl_company, dl_company2;
    private String di_class_no, di_class_no2;
    private String di_etc_otc_code, di_etc_otc_code2;
    private String chart, chart2;
    private String print_back, print_back2;
    private String print_front, print_front2;
    private String url, url2;
    byte[] img, img2;
    
    public void init(){
        flag = -1;
        dl_name = null;
        dl_company = null;
        di_class_no = null;
        di_etc_otc_code = null;
        chart = null;
        print_back = null;
        print_front = null;
        url = null;
        img = null;

        flag2 = -1;
        dl_name2 = null;
        dl_company2 = null;
        di_class_no2 = null;
        di_etc_otc_code2 = null;
        chart2 = null;
        print_back2 = null;
        print_front2 = null;
        url2 = null;
        img2 = null;
    }


    public void setFlag(int flag){
        this.flag = flag;
    }

    public int getFlag(){
        return this.flag;
    }

    public String getName() {
        return dl_name;
    }


    public void setName(String dl_name) {
        this.dl_name = dl_name;
    }


    public String getCompany() {
        return dl_company;
    }


    public void setCompany(String dl_company) {
        this.dl_company = dl_company;
    }


    public String getClass_no() {
        return di_class_no;
    }


    public void setClass_no(String di_class_no) {
        this.di_class_no = di_class_no;
    }


    public String getOtc_code() {
        return di_etc_otc_code;
    }


    public void setOtc_code(String di_etc_otc_code) {
        this.di_etc_otc_code = di_etc_otc_code;
    }


    public String getChart() {
        return chart;
    }


    public void setChart(String chart) {
        this.chart = chart;
    }


    public String getPrint_back() {
        return print_back;
    }


    public void setPrint_back(String print_back) {
        this.print_back = print_back;
    }


    public String getPrint_front() {
        return print_front;
    }


    public void setPrint_front(String print_front) {
        this.print_front = print_front;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }


    public byte[] getImg() {
        return img;
    }


    public void setImg(byte[] img) {
        this.img = img;
    }

    


    public String getName2() {
        return dl_name2;
    }

    public void setName2(String dl_name2) {
        this.dl_name2 = dl_name2;
    }

    public String getCompany2() {
        return dl_company2;
    }

    public void setCompany2(String dl_company2) {
        this.dl_company2 = dl_company2;
    }
    
    public String getClass_no2() {
        return di_class_no2;
    }
    
    public void setClass_no2(String di_class_no2) {
        this.di_class_no2 = di_class_no2;
    }

    public String getOtc_code2() {
        return di_etc_otc_code2;
    }

    public void setOtc_code2(String di_etc_otc_code2) {
        this.di_etc_otc_code2 = di_etc_otc_code2;
    }

    public String getChart2() {
        return chart2;
    }

    public void setChart2(String chart2) {
        this.chart2 = chart2;
    }

    public String getPrint_front2() {
        return print_front2;
    }

    public void setPrint_front2(String print_front2) {
        this.print_front2 = print_front2;
    }

    public String getPrint_back2() {
        return print_back2;
    }

    public void setPrint_back2(String print_back2) {
        this.print_back2 = print_back2;
    }

    public String getUrl2(){
        return url2;
    }

    public void setUrl2(String url2){
        this.url2 = url2;
    }
}