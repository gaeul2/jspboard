package model1;

public class BoardDTO {
    private int num;
    private String writer;
    private String title;
    private String content;
    private String subject;
    private String category;
    private String type;
    private String created_at;
    private String pass;
    private int hit;
    private String file_name;
    private String save_file_name;
    
    public int getNum() {
        return num;
    }
    
    public void setNum(int num) {
        this.num = num;
    }
    
    public String getWriter() {
        return writer;
    }
    
    public void setWriter(String writer) {
        this.writer = writer;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getCreated_at() {
        return created_at;
    }
    
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    
    public String getPass() {
        return pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public int getHit() {
        return hit;
    }
    
    public void setHit(int hit) {
        this.hit = hit;
    }
    
    public String getFile_name() {
        return file_name;
    }
    
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
    
    public String getSave_file_name() {
        return save_file_name;
    }
    
    public void setSave_file_name(String save_file_name) {
        this.save_file_name = save_file_name;
    }
}
