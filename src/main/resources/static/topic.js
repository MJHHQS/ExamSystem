var exam_time = document.getElementById("exam_time");
var exam_commit = document.getElementById("exam_commit");
var exam_commit_p = document.getElementById("exam_commit_p");
var timeCount = 0;
var time = setInterval(function() {
	timeCount++;
	var min = Math.floor(timeCount / 60);
	if (min >= 40) {
        exam_commit.setAttribute("class" , "exam_commit_class") ; 
        exam_commit_p.style.top=126+"px" ; 
	} else {
		var sec = timeCount % 60;
		exam_time.innerHTML = ((min > 9 ? min : "0" + min) + ":" + (sec > 9 ? sec : "0" + sec));
	}
}, 1000); 
