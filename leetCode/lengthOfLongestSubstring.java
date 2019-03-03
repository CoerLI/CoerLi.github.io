class Solution {
	// leetCode 3:无重复字符的最长子串
	// 双指针，巧妙的地方在不断弹出直到不包含重复
	public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() < 1)
        	return 0;
        int left = 0;
        int right = 0;
        int maxSize = 0;
        HashSet<Chracter> set = new HashSet<>();
        while(left < right && right < s.length()){
        	if(!set.contaisn(s.charAt(right))){
        		set.add(s.charAt(right++));
        		maxSize = Math.max(maxSize,set.size());
        	} else{
        		// 重点：移出的不是重复的数字，而是重复数字前所有的数字
        		set.remove(s.charAt(left++));
        	}
        }
        return maxSize;
    }



	// 自己的做法：N^2，建立n个set，每个set保存字符，有重复时退出
    public int lengthOfLongestSubstring_2(String s) {
        if(s == null || s.length() <1)
        	return 0;
        int maxSize = 0;
        for(int i = 0; i < s.length(); i++){
        	HashSet<Chracter> set = new HashSet<>();
        	for(int j = i; j < s.length(); j++){
        		if(set.contains(s.charAt(j)))
        			break;
        		set.add(s.charAt(j));
        		maxSize = Math.max(maxSize,set.size());
        	}
        }
        return maxSize;
    }
}