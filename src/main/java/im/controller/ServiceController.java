package im.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import im.dao.entity.ExamResult;
import im.dao.entity.Topic;
import im.dao.entity.TopicData;
import im.service.TopicService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ServiceController {

	
	public ServiceController() {

	}

	@Autowired
	TopicService topicService;

	@RequestMapping("/prepare")
	public String prepare() {
		return "index.html";
	}

	@RequestMapping("/start")
	public String start(HttpServletRequest request , HttpSession session) {
		Map<String, Object> topicSData = topicService.TopicSData();
		request.setAttribute(TopicService.TYPE_SINGLETON_STRING, topicSData.get(TopicService.TYPE_SINGLETON_STRING));
		request.setAttribute(TopicService.TYPE_DOUBLE_STRING, topicSData.get(TopicService.TYPE_DOUBLE_STRING));
		request.setAttribute(TopicService.TYPE_ESTIMATE_STRING, topicSData.get(TopicService.TYPE_ESTIMATE_STRING));
		session.setAttribute(TopicService.topicSData, topicSData.get(TopicService.topicSData));
		
		return "topic.html";
	}

	
	
	@PostMapping("/commit")
	public String commit(HttpServletRequest request , HttpSession session) {
		Enumeration<String> parameterNames = request.getParameterNames();
		TopicData topicData = (TopicData)session.getAttribute(TopicService.topicSData);
		int score = 0 ; 
		
		List<Topic> topicSSingleton = topicData.getTopicSSingleton();
		List<Topic> topicSDouble = topicData.getTopicSDouble();
		List<Topic> topicSEstimate = topicData.getTopicSEstimate();
		
		List<ExamResult> ExamResultSingleton = new ArrayList<>() ; 
		List<ExamResult> ExamResultDouble = new ArrayList<>() ; 
		List<ExamResult> ExamResultEstimate = new ArrayList<>() ; 
		
		
		Topic topic = null ; 
		for(;parameterNames.hasMoreElements();) {
			String nextElement = parameterNames.nextElement();
			
			if(nextElement.matches("^SINGLETON[0-9]{1,}$")) {
			
				int index =  TopicService.TopicPareIndex(nextElement , TopicService.TYPE_SINGLETON_STRING);
				topic = topicSSingleton.get(index) ; 
				String parameter = request.getParameter(nextElement);
				
				if(parameter.equals(topic.getAnswer())) {
					score += 2 ; 
				}
				ExamResultSingleton.add(new ExamResult(topic.getTitle(), topic.getAnswer(), parameter)) ; 
			}else if(nextElement.matches("^DOUBLE[0-9]{1,}$")) {
				int index =  TopicService.TopicPareIndex(nextElement , TopicService.TYPE_DOUBLE_STRING);
				topic = topicSDouble.get(index) ; 
				String[] parameter = request.getParameterValues(nextElement);
				
				String answer = "" ; 
				
				for(String str : parameter) {
					answer +=str ; 
				}
				
				if(answer.equals(topic.getAnswer())) {
					score += 4 ; 
				}
				
				ExamResultDouble.add(new ExamResult(topic.getTitle(), topic.getAnswer(), answer)) ; 
			}else if(nextElement.matches("^ESTIMATE[0-9]{1,}$")) {
				int index =  TopicService.TopicPareIndex(nextElement , TopicService.TYPE_ESTIMATE_STRING);
				topic = topicSEstimate.get(index) ; 
				String parameter = request.getParameter(nextElement);
				
				if(parameter.equals(topic.getAnswer())) {
					score += 2 ; 
				}
				ExamResultEstimate.add(new ExamResult(topic.getTitle(), topic.getAnswer(), parameter)) ; 
			}
		
			request.setAttribute("score", score) ; 
			request.setAttribute("examResultSingleton", ExamResultSingleton) ; 
			request.setAttribute("examResultDouble", ExamResultDouble) ; 
			request.setAttribute("examResultEstimate", ExamResultEstimate) ; 
		}
		return "result.html";
	}

	@PostMapping("/store")
	@ResponseBody
	public String store(Topic topic) {
		topicService.insertTopic(topic);
		return "ok";
	}

	@GetMapping("/store_topic")
	public String StoreTopic() {

		return "store.html";
	}

	@RequestMapping("/test")
	@ResponseBody
	public void test() {
		// System.out.println(topicMapper);
	}

}
