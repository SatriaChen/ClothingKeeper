export default {
	//获得当前日期
	get_now_formatDate: function(){
		  var date = new Date();
		  var seperator1 = "-";
		  var year = date.getFullYear();
		  var month = date.getMonth() + 1;
		  var strDate = date.getDate();
		  if (month >= 1 && month <= 9) {
		    month = "0" + month;
		  }
		  if (strDate >= 0 && strDate <= 9) {
		    strDate = "0" + strDate;
		  }
		  var currentdate = year + seperator1 + month + seperator1 + strDate;
		  return currentdate;
	},
	//当前日期加上4天
	get_end_date: function() {
		var date = new Date();
		var endDate = new Date(date.getTime() + 4 * 24 * 60 * 60 * 1000);//转化成毫秒
		var year=endDate.getFullYear();
		var month=endDate.getMonth()+1;
		var day=endDate.getDate();
		var CurrentDate=year+"-";
		if(month>=10)
		{
			CurrentDate=CurrentDate+month+"-";
		}
		else
		{
			CurrentDate=CurrentDate+"0"+month+"-";
		}
		if(day>=10)
		{
			CurrentDate=CurrentDate+day;
		}
		else
		{		
			CurrentDate=CurrentDate+"0"+day;
		}
		return CurrentDate;
	}
	
}