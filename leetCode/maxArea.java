class Solution {
    public int maxArea(int[] height) {
        if(height == null || height.length < 1)
        	return 0;
        int max = 0;
        int left = 0;
        int right = height.length-1;
        while(left<right){
        	max = Math.max(max,(right - left)*(Math.min(height[left],height[right])));
        	// 重点：面积被短线和横线决定，长线内移后短线不变，横线减小，面积一定减小
        	// 短线内移后，横线变短，但是短线有可能变成长线，面积可能增大，因此每次移动短线
        	if(height[left] <= height[right])
        		left++;
        	else
        		right--;
        }
        return max;
    }
}