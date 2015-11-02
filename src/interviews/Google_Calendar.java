package interviews;

import java.util.Date;

/**
 * 重复类型，你是按天，按周，按月，还是按年呢
 * 
 * 重复频率，即几个周期发生一次，如我每两个月去和朋友打一次球
 * 
 * 一个周期内发生的日期，比如你按周重复，那你是每周几发生呢，是周二，周四才发生，还是只周一才发生；
 * 如果是按月重复，是每月的第三个星期六发生，还是每月的23号发生呢；
 * 
 * 结束日期，即重复多久后终止事件本身。如你每个月要还房贷，也是有个最终还完的那天，比如30年后。
 * 比如每个月参加某个培训，只参加5次课堂培训就完了。
 * 
 * @author TinaLi
 *
 */
public class Google_Calendar {

}

/**
 * 日历事件/活动.
 */
class GoogleEvent {
	/**
	 * 事件标题
	 */
	private String title;

	/**
	 * 事件描述
	 */
	private String description;

	/**
	 * 事件发生地点
	 */
	private String location;

	/**
	 * 事件发生的开始时间
	 */
	private Date startDate;

	/**
	 * 事件发生的结束时间
	 */
	private Date endDate;

	/**
	 * 日历类型: 1,公历 2,农历
	 */
	private int calendarType;

	/**
	 * 重复事件规则表达式
	 */
	private String rule;
}