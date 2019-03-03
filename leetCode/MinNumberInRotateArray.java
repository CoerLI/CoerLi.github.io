package offer;

/**
 *
 */
public class MinNumberInRotateArray {
    public int minNumberInRotateArray(int [] array) {
    	if(array == null || array.length < 1 )
    		return 0;
        int left = 0;
        int right = array.length - 1;
        while(left <= right){
        	int mid = (right + left)/2;
        	if(array[left] < array[mid]){
        		left = mid;
        	}else if({
        		right = mid;
        	}
        }

    }
}
