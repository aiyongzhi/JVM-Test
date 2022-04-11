import heapGreater.heapGreater;

import java.util.*;

public class 中奖系统问题 {
    //写一个中奖机制，系统班第8节：加强堆练习题目
    public static class Customer {
        public int id;//用户编号
        public int buy;//购货件数
        public int enterTime;//入奖池或者候选池的时间点

        public Customer(int a, int b, int c) {
            id = a;
            buy = b;
            enterTime = c;
        }
    }

    //高效解法：加强堆
    public static class WhoIsTheDaddyList {
        private final HashMap<Integer, Customer> customers;
        private final heapGreater<Customer> candyHeap;
        private final heapGreater<Customer> daddyHeap;
        private final int daddyLimit;

        public WhoIsTheDaddyList(int limit) {
            daddyLimit = limit;
            customers = new HashMap<>();
            candyHeap = new heapGreater<Customer>(new candidateComparator());
            daddyHeap = new heapGreater<Customer>(new daddyComparator());
        }

        public void operate(int time, int id, boolean buyOrRefund) {
            //过滤无效事件
            if (!customers.containsKey(id) && !buyOrRefund) {
                return;
            }
            //1:buy==0,但现在进货
            //2:buy>0，现在买货或者进货物
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            Customer c = customers.get(id);
            if (buyOrRefund) {
                c.buy++;
            } else {
                c.buy--;
            }
            if (c.buy == 0) {
                customers.remove(id);
            }
            //在设置好入池
            if (!candyHeap.contains(c) && !daddyHeap.contains(c)) {
                if (daddyHeap.size() < daddyLimit) {
                    c.enterTime = time;
                    daddyHeap.push(c);
                } else {
                    c.enterTime = time;
                    candyHeap.push(c);
                }
            } else if (candyHeap.contains(c)) {
                if (c.buy == 0) {
                    candyHeap.remove(c);
                } else {
                    candyHeap.resign(c);
                }
            } else {
                if (c.buy == 0) {
                    daddyHeap.remove(c);
                } else {
                    daddyHeap.resign(c);
                }
            }
            daddyMove(time);
        }

        public List<Integer> getAllDaddies() {
            List<Customer> daddies = daddyHeap.getAllElement();
            List<Integer> ans = new ArrayList<>();
            for (Customer c : daddies) {
                ans.add(c.id);
            }
            return ans;
        }

        private void daddyMove(int time) {
            if (candyHeap.isEmpty()) {
                return;
            }
            if (daddyHeap.size() < daddyLimit) {
                Customer newDaddy = candyHeap.pop();
                newDaddy.enterTime = time;
                daddyHeap.push(newDaddy);
            } else if (candyHeap.peek().buy > daddyHeap.peek().buy) {
                Customer oldDaddy = daddyHeap.pop();
                Customer newDaddy = candyHeap.pop();
                oldDaddy.enterTime = time;
                newDaddy.enterTime = time;
                candyHeap.push(oldDaddy);
                daddyHeap.push(newDaddy);
            }
        }
    }

    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        WhoIsTheDaddyList whoIsTheDaddyList = new WhoIsTheDaddyList(k);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            whoIsTheDaddyList.operate(i, arr[i], op[i]);
            ans.add(whoIsTheDaddyList.getAllDaddies());
        }
        return ans;
    }

    public static int[] generateRandomArray(int maxId, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxId) + 1;
        }
        return arr;
    }

    public static boolean[] generateRandomOp(int len) {
        boolean[] op = new boolean[len];
        for (int i = 0; i < op.length; i++) {
            op[i] = Math.random() < 0.7;
        }
        return op;
    }

    public static class arrayComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static void sort(List<List<Integer>> List) {
        for (List<Integer> list : List) {
            list.sort(new arrayComparator());
        }
    }

    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static boolean[] copyArray(boolean[] arr) {
        boolean[] ans = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxK = 10;
        int maxLen = 100;
        int maxId = 100;
        int testTime = 1000000;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxK) + 1;
            int[] arr = generateRandomArray(maxId, maxLen);
            boolean[] op = generateRandomOp(arr.length);
            List<List<Integer>> ans1 = topK(arr, op, k);
            List<List<Integer>> ans2 = compare(arr, op, k);
            sort(ans1);
            sort(ans2);
            if (!ans1.equals(ans2)) {
                success = false;
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(op));
                System.out.println("k===" + k);
                System.out.println(Arrays.toString(ans1.toArray()));
                System.out.println(Arrays.toString(ans2.toArray()));
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Fucking fucked!");
    }

    //写一个暴力方法，做对数器
    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
        HashMap<Integer, Customer> map = new HashMap<>();
        ArrayList<Customer> candy = new ArrayList<>();//候选池
        ArrayList<Customer> daddy = new ArrayList<>();//奖池
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            //先把无效事件过滤掉，即buy==0,又选择退货
            if (!map.containsKey(id) && !buyOrRefund) {
                ans.add(getCurAns(daddy));
                continue;
            }
            //1:buy==0，此时买货
            //2:buy>0,此时可能买货也可能买货
            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            Customer c = map.get(id);
            if (buyOrRefund) {
                c.buy++;
            } else {
                c.buy--;
            }
            //第一次入池
            if (c.buy == 0) {
                map.remove(id);
            }
            if (!candy.contains(c) && !daddy.contains(c)) {
                if (daddy.size() < k) {
                    c.enterTime = i;
                    daddy.add(c);
                } else {
                    c.enterTime = i;
                    candy.add(c);
                }
            }
            //如果用户的buy==0的，则在两个池中清空他的信息
            cleanZeroBuy(candy);
            cleanZeroBuy(daddy);
            candy.sort(new candidateComparator());
            daddy.sort(new daddyComparator());
            move(candy, daddy, i, k);
            ans.add(getCurAns(daddy));
        }
        return ans;
    }

    //写一个move方法：移动候选池中的用户到奖池中
    public static void move(ArrayList<Customer> candy, ArrayList<Customer> daddy, int time
            , int k) {
        if (candy.size() > 0) {
            Customer bestCandy = candy.get(0);
            if (daddy.size() < k) {
                candy.remove(bestCandy);
                bestCandy.enterTime = time;
                daddy.add(bestCandy);
            } else if (bestCandy.buy > daddy.get(0).buy) {
                Customer minDaddy = daddy.get(0);
                minDaddy.enterTime = time;
                bestCandy.enterTime = time;
                candy.remove(bestCandy);
                daddy.remove(minDaddy);
                candy.add(minDaddy);
                daddy.add(bestCandy);
            }
        }
    }

    public static class candidateComparator implements Comparator<Customer> {
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
        }
    }

    public static class daddyComparator implements Comparator<Customer> {
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
        }
    }

    //写一个方法，清空池中buy==0的用户
    public static void cleanZeroBuy(ArrayList<Customer> date) {
        ArrayList<Customer> noZero = new ArrayList<>();
        for (Customer customer : date) {
            if (customer.buy != 0) {
                noZero.add(customer);
            }
        }
        date.clear();
        for (Customer customer : noZero) {
            date.add(customer);
        }
    }

    //写一个方法，从奖池中获取当前的名单id
    public static List<Integer> getCurAns(ArrayList<Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (Customer c : daddy) {
            ans.add(c.id);
        }
        return ans;
    }

}
