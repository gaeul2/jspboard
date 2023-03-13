package Util;

import model1.BoardDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.util.Map;

public class Validations {
    public void typeMakeSentence(BoardDTO bean, String[] type) {
        StringBuilder types = new StringBuilder();
        String sentence;
        for (String s : type) {
            types.append(s).append(" ");
        }
        sentence = types.toString().trim().replace(" ", ", ");
        bean.setType(sentence);
    }

    public void changeFileName(BoardDTO bdto, String fileName, String saveDirectory) {
        String ext = fileName.substring(fileName.lastIndexOf("."));
        String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
        String newFileName = now + ext;

        File old = new File(saveDirectory + File.separator + fileName);
        File newFile = new File(saveDirectory + File.separator + newFileName);
        //저장되는 파일 이름 변경
        old.renameTo(newFile);
        bdto.setFile_name(fileName);
        bdto.setSave_file_name(newFile.toString());

//        FileUtil.deleteFile(req, "/uploads", )
    }

    public Map<String, Object> searchWordValidation(Map<String, Object> map) {
        String title = (String) map.get("title");
        String writer = (String) map.get("writer");

        if (title == "" & writer == "" ) {
            return map;
        } else {
            if (title != "") {
                title = "'%" + title + "%'";
                map.put("title", title);
            }
            if (writer != "") {
                writer = "'%" + writer + "%'";
                map.put("writer", writer);
            }
        }
        return map;
        }
}
