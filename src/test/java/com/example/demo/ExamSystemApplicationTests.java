package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

//@SpringBootTest
class ExamSystemApplicationTests {
	
	@Test
	void context1() {
		System.out.println(TopicPareIndex("SINGLETON10" , "SINGLETON"));
	//	System.out.println(Integer.valueOf("SINGLETON10".substring("SINGLETON10".lastIndexOf("SINGLETON")+"SINGLETON".length())));
	 // System.out.println(Arrays.toString("A. public".split("\\.")));
	}
	
	public int TopicPareIndex(String name , String previ) {
		return Integer.valueOf(name.substring(name.lastIndexOf(previ)+previ.length()));
	}
	
//A. int|-|B. float|-|C. String|-|D. boolean
	@Test
	void context() {
		String str = "A. int|-|B. float|-|C. String|-|D. boolean" ; 
	//	String[] split1 = StringUtils.split(str,"|-|-"); 
		List<String> split12 = split1(new String[] {str} , new ArrayList<String>()); 
		String[] array = split12.toArray(new String[] {});
		System.out.println(Arrays.toString(array));
	}

	public List<String> split1(String[] datas , List<String> list) {
		  for(String str : datas) {
			  String[] split1 = StringUtils.split(str,"|-|"); 
			  if(split1==null) {
				  list.add(str) ; 
			  }else {
				  split1(split1 , list) ; 
				  
			  }
		  }
		return list ; 
	}

}

