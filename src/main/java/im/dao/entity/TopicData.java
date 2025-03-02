package im.dao.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class TopicData implements Serializable {

	private static final long serialVersionUID = 1L;

	List<Topic> topicSSingleton ; 
	 
	List<Topic> topicSDouble  ; 
	 
	List<Topic> topicSEstimate  ;

	public TopicData(List<Topic> topicSSingleton , List<Topic> topicSDouble ,
			List<Topic> topicSEstimate) {
		this.topicSSingleton = topicSSingleton ; 
		this.topicSDouble = topicSDouble ; 
		this.topicSEstimate = topicSEstimate ; 
	}
	
	public List<Topic> getTopicSSingleton() {
		return topicSSingleton;
	}

	public void setTopicSSingleton(List<Topic> topicSSingleton) {
		this.topicSSingleton = topicSSingleton;
	}

	public List<Topic> getTopicSDouble() {
		return topicSDouble;
	}

	public void setTopicSDouble(List<Topic> topicSDouble) {
		this.topicSDouble = topicSDouble;
	}

	public List<Topic> getTopicSEstimate() {
		return topicSEstimate;
	}

	public void setTopicSEstimate(List<Topic> topicSEstimate) {
		this.topicSEstimate = topicSEstimate;
	}

	public int hashCode() {
		return Objects.hash(topicSDouble, topicSEstimate, topicSSingleton);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TopicData other = (TopicData) obj;
		return Objects.equals(topicSDouble, other.topicSDouble) && Objects.equals(topicSEstimate, other.topicSEstimate)
				&& Objects.equals(topicSSingleton, other.topicSSingleton);
	}

	public String toString() {
		return "TopicData [topicSSingleton=" + topicSSingleton + ", topicSDouble=" + topicSDouble + ", topicSEstimate="
				+ topicSEstimate + "]";
	} 
	 
	
	
}
