package im.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import im.dao.TopicMapper;
import im.dao.entity.Topic;
import im.dao.entity.TopicData;

@Service
public class TopicService {

	@Autowired
	TopicMapper topicMapper;

	public static final int TYPE_SINGLETON = 0;

	public static final int TYPE_DOUBLE = 1;

	public static final int TYPE_ESTIMATE = 2;

	public static final int SET_JAVA = 1;

	public static final int SET_HTML_JS = 2;

	public static final int BUCKET_SIZE = 40;

	public static final String TYPE_SINGLETON_STRING = "SINGLETON";

	public static final String TYPE_DOUBLE_STRING = "DOUBLE";

	public static final String TYPE_ESTIMATE_STRING = "ESTIMATE";

	public static final String topicSData = "topicSData";

	public List<Topic> TopicS(int set, int type) {
		int topicCount = topicMapper.TopicCount(set, type);
		List<Topic> topics = null;
		if (topicCount > BUCKET_SIZE) {
			int width = ((int) (Math.random() * (topicCount - BUCKET_SIZE)));
			topics = topicMapper.TopicS(set, type, width, BUCKET_SIZE);
		} else {
			topics = topicMapper.TopicS(set, type, 0, BUCKET_SIZE);
		}
		return topics;
	}

	public Map<String, Object> TopicSData() {
		// 单项选择题 0 , 多项选择题 1 , 判断 2
		Map<String, Object> data = new HashMap<>();
		List<Topic> topicSSingleton = TopicS(SET_JAVA, TYPE_SINGLETON);
		// System.out.println(topicSSingleton.get(0).getChanges());
		topicSSingleton = popTopic(topicSSingleton , 20) ; 
		data.put(TYPE_SINGLETON_STRING, topicSSingleton);
		
		List<Topic> topicSDouble = TopicS(SET_JAVA, TYPE_DOUBLE);
		topicSDouble = popTopic(topicSDouble , 10) ; 
		data.put(TYPE_DOUBLE_STRING, topicSDouble);
		
		List<Topic> topicSEstimate = TopicS(SET_JAVA, TYPE_ESTIMATE);
		topicSEstimate = popTopic(topicSEstimate , 10) ; 
		data.put(TYPE_ESTIMATE_STRING, topicSEstimate);
		TopicData topicData = new TopicData(topicSSingleton, topicSDouble, topicSEstimate);
		data.put(topicSData, topicData);

		return data;
	}

	public void insertTopic(Topic topic) {
		topicMapper.insertTopic(topic);
	};

	public List<Topic> popTopic(List<Topic> topics, int size) {

		List<Topic> result = new ArrayList<>() ; 
		if (topics.size() > size) {
			Set<Integer> indexs = new HashSet<>();
			for (; indexs.size() < size;) {
				int index = ((int) (Math.random() * size));
				indexs.add(index);
			}
			for(int i = 0 ; i < indexs.size() ; i++ ) {
				result.add(topics.get(i)) ; 
			}
			
		}else {
			result = topics ; 
		}
		
		return result ; 
	}

	public static int TopicPareIndex(String name, String previ) {
		return Integer.valueOf(name.substring(name.lastIndexOf(previ) + previ.length()));
	}

}
