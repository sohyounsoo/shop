package jsp.product.model;
/**
 * This domain object represents a Member.
 * The data attributes are all package-private to allow access to them
 * in the {@link MemberService} class.
 */
public class Member {
    
    String memID;
    String password;
    String name;
    String ssn;
    String email;
    String tel;
    String zipcode;
    String address;
    int point;
    String memberDate;
    String Check = "N";
    
    public Member() {}
    /**
     * This is the constructor.  I tis package-private to prevent
     * misuse.  The {@link RegisterMember} method should be used to
     * create a new Member object.
     */
    public Member(String memID) {
        this(memID, "", "", "", "","", "", "", -1, "");
    }
    
    /**
     * This is the full constructor.
     */
    public Member(String memID, String password, String name, String ssn,
            String email, String tel,String zipcode,String address,int point,String memberDate) {
        this.memID = memID;
        this.password = password;
        this.name = name;
        this.ssn = ssn;
        this.email = email;
        this.tel = tel;
        this.zipcode = zipcode;
        this.address = address;
        this.point = point;
        this.memberDate = memberDate;
    }
    
    public void setMemID(String memID) {
        this.memID = memID;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPoint(int point) {
        this.point = point;
    }
    public void setMemberDate(String memberDate) {
        this.memberDate = memberDate;
    }
     public void setCheck(String Check) {
        this.Check = Check;
    }
    
    public String getMemID() {
        return memID;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getSsn() {
        return ssn;
    }
    public String getEmail() {
        return email;
    }
    public String getTel() {
        return tel;
    }
    public String getZipcode() {
        return zipcode;
    }
    public String getAddress() {
        return address;
    }
    public int getPoint() {
        return point;
    }
    public String getMemberDate() {
        return memberDate;
    }
    public String getCheck() {return Check;}
    
}
