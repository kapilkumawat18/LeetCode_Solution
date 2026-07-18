class Solution {
    public static int findGCD(int[] nums) {
        int k = nums.length;
        Arrays.sort(nums);
        int largest = nums[k-1];
        int smallest = nums[0];
        return gcd(smallest,largest);

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