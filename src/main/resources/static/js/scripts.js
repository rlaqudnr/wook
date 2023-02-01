var over;
$("#overlappedID").click(function() {
	over = 1;

	const id = $("#userId").val();
	$.ajax({
		type: "get",
		async: false,
		url: "http://localhost:8080/users/idcheck",
		data: { id: id },
		success: function(data) {
			if (data == 1) {
				$("#signup").attr("type", "button");
				$("#olmessage").text("이미 사용중인 ID 입니다.");

				$("#olmessage").addClass("olmessagef");
				$("#olmessage").removeClass("olmessaget");
			} else {
				$("#olmessage").text("사용 가능한 ID 입니다.");
				$("#olmessage").addClass("olmessaget");
				$("#olmessage").removeClass("olmessagef");
				$("#signup").attr("type", "submit");
			}
		}
	})
});





$("#userId").keyup(function() {


	$("#olmessage").text("중복 확인을 해주세요.");
	$("#signup").attr("type", "button");
	$("#olmessage").addClass("olmessagef");
	$("#olmessage").removeClass("olmessaget");


});






$("#signup").click(function() {
	if (!over == 1) {

		$("#olmessage").text("중복 확인을 해주세요.");
		$("#signup").attr("type", "button");
		$("#olmessage").addClass("olmessagef");
		$("#olmessage").removeClass("olmessaget");

	}

}
)




	  
	 
	
	




