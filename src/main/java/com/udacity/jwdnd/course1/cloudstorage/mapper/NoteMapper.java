package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) " +
            "VALUES(#{title}, #{description}, #{userId})")
    int addNote(Note note);

    @Select("SELECT * FROM NOTES where userid = #{userId}")
    Note[] getNotes(Integer userId);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    int deleleNote(Integer noteId);

    @Update("UPDATE NOTES SET notetitle = #{title}, notedescription = #{description} WHERE noteid = #{noteId}")
    int updateNote(Note note);
}
