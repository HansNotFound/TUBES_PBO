/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author NealsonW
 */
public class Comment {
    String nicknameComment;
    String isiComment;

    public Comment() {
    }

    public Comment(String nicknameComment, String isiComment) {
        this.nicknameComment = nicknameComment;
        this.isiComment = isiComment;
    }

    public String getNicknameComment() {
        return nicknameComment;
    }

    public void setNicknameComment(String nicknameComment) {
        this.nicknameComment = nicknameComment;
    }

    public String getIsiComment() {
        return isiComment;
    }

    public void setIsiComment(String isiComment) {
        this.isiComment = isiComment;
    }
    
    
}