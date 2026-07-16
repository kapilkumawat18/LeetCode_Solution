class Solution {
    public static int[] shuffle(int[] nums, int n) {
        int[] output = new int[2*n];
        for(int i=0;i<n;i++){
                output[2*i] = nums[i];
                output[2*i+1] = nums[i+n];
        }
        return output;

    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int nums[] = new int[2*n];
        for(int i=0;i<2*n;i++){
            nums[i] = in.nextInt();
        }
        int result[] = shuffle(nums,n);
        for(int i=0;i<result.length;i++){
            System.out.print(result[i]+",");
        }
    }
}