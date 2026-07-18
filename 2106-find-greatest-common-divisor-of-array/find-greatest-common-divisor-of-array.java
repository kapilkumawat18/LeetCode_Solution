class Solution {
    public static int findGCD(int[] nums) {
        int k = nums.length;
        int max = nums[0];
        int min = nums[0];
        for(int i=1;i<k;i++){
            if(nums[i]>max){
                max = nums[i];
            }
            if(nums[i]<min){
                min = nums[i];
            }
        }
        return gcd(min,max);

    }
    public static int gcd(int a , int b){
        if(b==0){
            return a;
        }else{
            return gcd(b,a%b);
        }
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = in.nextInt();
        }
        int result = findGCD(nums);
        System.out.print(result);
    }
}