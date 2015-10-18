package interviews;

public class Palantir_Get_Suspicious_People_List {
	/**
	 * input String[] 是一堆transaction的记录： <name>|<amount USD>|<City>|<Time>
	 * 
	 * 找出所有有问题的transaction。有问题的definition是，满足下列任何一条即可： 
	 * 1. amount > 3000 
	 * 2. 同一人在一小时内有两个或以上的transaction不在同一个city 
	 * 
	 * output: 一个人名的list，要按照出问题的时间顺序排列。
	 * 排列时，如果有人出现第二种问题，那么他的出问题时间要按照第一次suspicious
	 * transaction的时间计算。
	 */
	
}
