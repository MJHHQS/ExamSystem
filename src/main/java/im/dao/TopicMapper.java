package im.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import im.dao.entity.Topic;

@Mapper
public interface TopicMapper {

	@Insert("insert into topic_table (title,answer,content,set_ ,type) "
			+ "values (#{title} , #{answer}, #{content}, #{set_}, #{type})")
	public void insertTopic(Topic topic) ; 
	 
	@Select("select tid , title , answer , content , set_  , type from topic_table where "
			+ "set_ = #{set_} and type = #{type} limit #{start} , #{offset}")
	public List<Topic> TopicS(@Param("set_") int set , @Param("type") int type , 
			@Param("start") int start , @Param("offset") int offset) ; 
	
	@Select("select count(tid) from topic_table where set_ = #{set_} and type = #{type}")
	public int TopicCount(@Param("set_") int set , @Param("type") int type) ; 
	
}

