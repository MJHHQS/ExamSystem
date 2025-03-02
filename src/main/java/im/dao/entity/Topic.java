package im.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.util.StringUtils;

public class Topic implements Serializable {

	private static final long serialVersionUID = 1L;

	int tid;
	String title;
	String answer;
	String content;

	String[] changes;
	String[] changeValues;

	int set_;
	int type;

	public Topic() {
	}

	public Topic(String title, String answer, String content, int set_, int type) {
		this.title = title;
		this.answer = answer;
		this.content = content;
		this.set_ = set_;
		this.type = type;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getContent() {
		return content;
	}

	public void init() {
		// System.out.println("init()");
		if (!Objects.isNull(content)) {
			List<String> changes = split1(new String[] { content }, new ArrayList<String>());
			this.changes = changes.toArray(new String[] {});
			String[] changeValues = new String[changes.size()];

			if (this.type == 2) {
				for (int i = 0; i < this.changes.length; i++) {
					String string = this.changes[i];
                    changeValues[i] = string;
				}
			} else {
                for (int i = 0; i < this.changes.length; i++) {
					String string = this.changes[i];
					String[] split = string.split("\\.");
					if(split.length>1) {
						changeValues[i] = split[0].trim();
					}else {
						System.out.println("error : content 解析答案时不正确 ！tid: "+this.tid);
					}
				}
			}
			this.changeValues = changeValues ; 
		}

	}
	
	
	public void setContent(String content) {
		this.content = content;
    }

	public int getSet_() {
		return set_;
	}

	public void setSet_(int set_) {
		this.set_ = set_;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		init() ; 
	}

	public String[] getChanges() {
		return changes;
	}

	public void setChanges(String[] changes) {
		this.changes = changes;
	}

	public String[] getChangeValues() {
		return changeValues;
	}

	public void setChangeValues(String[] changeValues) {
		this.changeValues = changeValues;
	}

	public List<String> split1(String[] datas, List<String> list) {
		for (String str : datas) {
			String[] split1 = StringUtils.split(str, "|-|");
			if (split1 == null) {
				list.add(str);
			} else {
				split1(split1, list);

			}
		}
		return list;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(changeValues);
		result = prime * result + Arrays.hashCode(changes);
		result = prime * result + Objects.hash(answer, content, set_, tid, title, type);
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		return Objects.equals(answer, other.answer) && Arrays.equals(changeValues, other.changeValues)
				&& Arrays.equals(changes, other.changes) && Objects.equals(content, other.content) && set_ == other.set_
				&& tid == other.tid && Objects.equals(title, other.title) && type == other.type;
	}

	public String toString() {
		return "Topic [tid=" + tid + ", title=" + title + ", answer=" + answer + ", content=" + content + ", changes="
				+ Arrays.toString(changes) + ", changeValues=" + Arrays.toString(changeValues) + ", set_=" + set_
				+ ", type=" + type + "]";
	}

}
