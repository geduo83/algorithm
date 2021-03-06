package Z2021.a0308;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import Z2019.A003.TreeNode;

public class Test {
    //1.节点数
    static int getTreeNodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + getTreeNodeCount(root.leftNode) + getTreeNodeCount(root.rightNode);
    }

    //2.前序，中序，后续遍历
    static void printlnTreeNode(TreeNode root) {
        if (root == null) {
            return;
        }
        printlnTreeNode(root.leftNode);
        System.out.println(root.val);
        printlnTreeNode(root.rightNode);
    }

    //3.迭代法遍历,通过栈实现深度遍历
    static List<TreeNode> getTreeNode(TreeNode root) {
        List<TreeNode> list = null;
        try {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            list = new ArrayList<>();
            while (!stack.isEmpty()) {
                TreeNode peek = stack.peek();
                if (peek != null) {
                    TreeNode pop = stack.pop();


                    if (pop.rightNode != null) {
                        stack.push(pop.rightNode);
                    }

                    if (pop.leftNode != null) {
                        stack.push(pop.leftNode);
                    }

                    stack.push(pop);
                    stack.push(null);
                } else {
                    stack.pop();
                    list.add(stack.pop());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //4.层序遍历，借助队列实现
    static List<List<TreeNode>> getTreeNode1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<TreeNode>> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> list1 = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                list1.add(poll);
                if (poll.leftNode != null) {
                    queue.offer(poll.leftNode);
                }
                if (poll.rightNode != null) {
                    queue.offer(poll.rightNode);
                }
            }
            list.add(list1);
        }
        return list;
    }

    //5.求最大深度
    static int getTreeNodeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 1 + getTreeNodeHeight(root.leftNode);
        int right = 1 + getTreeNodeHeight(root.rightNode);
        return left > right ? left : right;
    }

    //6.相同的
    static boolean isSameTreeNode(TreeNode t1, TreeNode t2) {
        if (t1 != null && t2 == null) {
            return false;
        }
        if (t1 == null && t2 != null) {
            return false;
        }
        if (t1 == null && t2 == null) {
            return true;
        }
        return t1.val == t2.val && isSameTreeNode(t1.leftNode, t2.leftNode) && isSameTreeNode(t1.rightNode, t2.rightNode);
    }

    //7.对称的
    static boolean isSymTreeNode(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 != null) {
            return false;
        }
        if (t1 != null && t2 == null) {
            return false;
        }
        if (t1 == null && t2 == null) {
            return true;
        }
        return t1.val == t2.val && isSymTreeNode(t1.leftNode, t2.rightNode) && isSymTreeNode(t1.rightNode, t2.leftNode);
    }

    //8.翻转一个二叉树
    //8.1递归
    //8.2迭代法稍加改造，也可以实现
    static TreeNode resverseTreeNode(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode temp = root.rightNode;
        root.rightNode = root.leftNode;
        root.leftNode = temp;

        resverseTreeNode(root.leftNode);
        resverseTreeNode(root.rightNode);

        return root;
    }

    //9.最大二叉树
    static TreeNode createTreeNode(int[] nums, int l, int r) {
        if (l == r) {
            return null;
        }
        int max = -1;
        int index = -1;
        for (int i = l; i < r; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        TreeNode maxNode = new TreeNode(max);
        maxNode.leftNode = createTreeNode(nums, l, index);
        maxNode.rightNode = createTreeNode(nums, index + 1, r);
        return maxNode;
    }

    //10.合并两个
    static TreeNode createTreeNode1(TreeNode t1, TreeNode t2) {
        if (t1 != null && t2 == null) {
            return t1;
        }
        if (t1 == null && t2 != null) {
            return t2;
        }
        if (t1 == null && t2 == null) {
            return null;
        }
        TreeNode node = new TreeNode(t1.val + t2.val);
        node.leftNode = createTreeNode1(t1.leftNode, t2.leftNode);
        node.rightNode = createTreeNode1(t1.rightNode, t2.rightNode);
        return node;
    }

    //11.查找节点
    static TreeNode searchBinaryTreeNode(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        if (target > root.val) {
            return searchBinaryTreeNode(root.rightNode, target);
        } else if (target < root.val) {
            return searchBinaryTreeNode(root.leftNode, target);
        } else if (root.val == target) {
            return root;
        }
        return null;
    }

    //12.验证,模拟一个中序遍历
    static int val = 0;
    static boolean flag = false;

    static boolean isBinaryTreeNode(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isBinaryTreeNode(root.leftNode);
        if (!flag) {
            val = root.val;
            flag = true;
        } else {
            if (val < root.val) {
                val = root.val;
            } else {
                return false;
            }
        }
        boolean right = isBinaryTreeNode(root.rightNode);
        return left && right;
    }

    //13.添加节点
    static TreeNode insertTreeNode(TreeNode root, int target) {
        if (root == null) {
            return new TreeNode(target);
        }
        if (root.val > target) {
            root.leftNode = insertTreeNode(root.leftNode, target);
        }
        if (root.val < target) {
            root.rightNode = insertTreeNode(root.rightNode, target);
        }
        return root;
    }

    //14.删除节点
    static TreeNode removeTreeNode(TreeNode root, int target) {
        if (root.val == target) {
            if (root.leftNode == null) {
                return root.rightNode;
            } else if (root.rightNode == null) {
                return root.leftNode;
            } else {
                TreeNode node = root.rightNode;
                while (node.leftNode != null) {
                    node = node.leftNode;
                }
                node.leftNode = root.leftNode;
                root = root.rightNode;
                return root;
            }
        } else if (root.val < target) {
            root.rightNode = removeTreeNode(root.rightNode, target);
        } else {
            root.leftNode = removeTreeNode(root.leftNode, target);
        }
        return root;
    }

    //1.查找
    static TreeNode searchTreeNode11(TreeNode root, int target) {
        if (root == null) {
            return null;
        } else if (root.val > target) {
            return searchTreeNode11(root.leftNode, target);
        } else if (root.val < target) {
            return searchTreeNode11(root.rightNode, target);
        } else {
            return root;
        }
    }

    //2.迭代法
    static TreeNode searchTreeNode22(TreeNode root, int target) {
        while (root != null) {
            if (root.val > target) {
                root = root.leftNode;
            } else if (root.val < target) {
                root = root.rightNode;
            } else {
                return root;
            }
        }
        return null;
    }

    //验证:中序遍历和单调递曾是保持一致的,左子树和右子数都为真
    static int min = 0;
    static boolean flag1 = false;

    static boolean isBinaryTreeNode11(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isBinaryTreeNode11(root.leftNode);
        if (!flag1) {
            min = root.val;
            flag1 = true;
        } else {
            if (min > root.val) {
                return false;
            }
        }
        boolean right = isBinaryTreeNode11(root.rightNode);
        return left && right;
    }

    //插入元素
    static TreeNode insertTreeNode11(TreeNode root, int target) {
        if (root == null) {
            return new TreeNode(target);
        }
        if (root.val > target) {
            root.leftNode = insertTreeNode11(root.leftNode, target);
        } else {
            root.rightNode = insertTreeNode11(root.rightNode, target);
        }
        return root;
    }

    //1.remove四种情况
    static TreeNode removeTreeNode11(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        if (root.val > target) {
            root.leftNode = removeTreeNode11(root.leftNode, target);
        } else if (root.val < target) {
            root.rightNode = removeTreeNode11(root.rightNode, target);
        } else {
            if (root.leftNode != null && root.rightNode == null) {
                return root.leftNode;
            }
            if (root.leftNode == null && root.rightNode != null) {
                return root.rightNode;
            } else {
                TreeNode curr = root.rightNode;
                while (curr.leftNode != null) {
                    curr = curr.leftNode;
                }
                curr.leftNode = root.leftNode;
                root = root.rightNode;
                return root;
            }
        }
        return root;
    }

    static TreeNode searchBinaryTreeNode11(TreeNode root, int target) {
        if(root == null){
            return null;
        }
        if (root.val > target) {
            return searchBinaryTreeNode11(root.leftNode, target);
        } else if (root.val < target) {
            return searchBinaryTreeNode(root.rightNode, target);
        } else {
            return root;
        }
    }
    static TreeNode searchBinaryTreeNode22(TreeNode root,int target){
        TreeNode curr = root;
        while (curr != null){
            if (curr.val > target) {
                curr = root.leftNode;
            } else if (curr.val < target) {
                curr = root.rightNode;
            } else {
                return curr;
            }
        }
        return null;
    }
    static boolean flag11 = false;
    static int min11 = 0;
    static boolean isVerifyBinaryTreeNode(TreeNode root){
        if(root == null){
            return true;
        }
        boolean left = isVerifyBinaryTreeNode(root.leftNode);
        if(!flag11){
            min11 = root.val;
            flag11 = true;
        }else{
            if(root.val < min11){
                return false;
            }
            min11 = root.val;
        }
        boolean right = isVerifyBinaryTreeNode(root.rightNode);
        return left && right;
    }

    static TreeNode insertTreeNode22(TreeNode root,int target){
        if(root == null){
            return new TreeNode(target);
        }
        if(root.val > target){
            root.leftNode = insertTreeNode22(root.leftNode,target);
        }else{
            root.rightNode =  insertTreeNode22(root.rightNode,target);
        }
        return root;
    }
    static TreeNode deletTreeNode11(TreeNode root,int target){
        if(root == null){
            return null;
        }
        if(root.val > target){
           root.leftNode = deletTreeNode11(root.leftNode,target);
        }else if(root.val < target){
            root.rightNode = deletTreeNode11(root.rightNode,target);
        }else{
            if(root.leftNode != null && root.rightNode == null){
                return root.leftNode;
            }else if(root.leftNode == null && root.rightNode != null){
                return root.rightNode;
            }else{
                TreeNode curr = root.rightNode;
                while (curr.leftNode != null){
                    curr = curr.leftNode;
                }
                curr.leftNode = root.leftNode;
                root = curr.rightNode;
                return root;
            }
        }
        return root;
    }
    static TreeNode mergeTreeNode(TreeNode t1,TreeNode t2){
        if(t1 != null && t2 == null){
            return t1;
        }
        if(t1 == null && t2 != null){
            return t2;
        }
        TreeNode root = new TreeNode(t1.val + t2.val);
        root.leftNode = mergeTreeNode(t1.leftNode,t2.leftNode);
        root.rightNode = mergeTreeNode(t1.rightNode,t2.rightNode);
        return root;
    }
    static TreeNode maxTreeNode(int[] nums,int l,int r){
        if(l == r){
            return null;
        }
        int max = -1;
        int index = -1;
        for(int i = l; i < r; i++){
            if(nums[i] > max){
                max = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.leftNode = maxTreeNode(nums,l,index - 1);
        root.rightNode = maxTreeNode(nums,index + 1,r);
        return root;
    }
    static TreeNode resverseTreeNode22(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode temp = root.leftNode;
        root.leftNode = root.rightNode;
        root.rightNode = temp;

        resverseTreeNode22(root.leftNode);
        resverseTreeNode22(root.rightNode);
        return root;
    }
    public static void main(String[] args) {
//        TreeNode root = new TreeNode(0);
//        TreeNode treeNode1 = new TreeNode(1);
//        TreeNode treeNode2 = new TreeNode(2);
//        TreeNode treeNode3 = new TreeNode(3);
//        TreeNode treeNode4 = new TreeNode(4);
//        TreeNode treeNode5 = new TreeNode(5);
//        TreeNode treeNode6 = new TreeNode(6);
//
//        root.leftNode = treeNode1;
//        root.rightNode = treeNode2;+

//
//        treeNode1.leftNode = treeNode3;
//        treeNode1.rightNode = treeNode4;
//
//        treeNode2.leftNode = treeNode5;
//        treeNode2.rightNode = treeNode6;
        //System.out.println(getTreeNodeCount(root));
        //printlnTreeNode(root);
//        List<TreeNode> treeNode = getTreeNode(root);
//        for (TreeNode node : treeNode) {
//            System.out.println(node.val);
//        }
//        List<List<TreeNode>> treeNode11 = getTreeNode1(root);
//        for (List<TreeNode> list : treeNode11) {
//            for (TreeNode treeNode : list) {
//                System.out.println(treeNode.val);
//            }
//        }
        //System.out.println(getTreeNodeHeight(root));
        //TreeNode treeNode = resverseTreeNode(root);
        //System.out.println(treeNode);
//        int[] nums = {3, 2, 1, 6, 0, 5};
//        TreeNode treeNode = createTreeNode(nums, 0, nums.length);
//        System.out.println(treeNode);
        //TreeNode treeNode11 = createTreeNode1(root, root);
        //System.out.println(treeNode11);
        TreeNode root = new TreeNode(4);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);

        root.leftNode = treeNode2;
        root.rightNode = treeNode6;

        treeNode2.leftNode = treeNode1;
        treeNode2.rightNode = treeNode3;

        treeNode6.leftNode = treeNode5;
        treeNode6.rightNode = treeNode7;
//        //TreeNode treeNode = searchBinaryTreeNode(root, 2);
//        //System.out.println(isBinaryTreeNode(root));
//        TreeNode o = insertTreeNode(root, 9);
//        System.out.println(o);
//        TreeNode o = searchTreeNode11(root, 3);
//        System.out.println(o.val);
        //System.out.println(isBinaryTreeNode11(root));
        //TreeNode treeNode = insertTreeNode11(root, 9);
        TreeNode treeNode = removeTreeNode11(root, 6);
        System.out.println("ok");
    }
}
