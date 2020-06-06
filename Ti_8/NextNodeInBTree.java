public class NextNodeInBTree {

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {

        if (pNode == null) {
            return null;
        }

//        if (pNode.left == null && pNode.right == null && pNode.next == null) {
//            return null;
//        }

        if (pNode.right != null) {
            TreeLinkNode node = pNode.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
//        else if (pNode.next.left == pNode) {
//            return pNode.next;
//        } else if (pNode.next.right == pNode) {
//            TreeLinkNode node = pNode;
//            while (node.next.left != node) {
//                node = node.next;
//            }
//            return node.next;
//        } else {
//            return null;
//        }

        while (pNode.next != null) {
            if (pNode == pNode.next.left) {
                return pNode.next;
            }
            pNode = pNode.next;
        }
        return null;
    }

}
