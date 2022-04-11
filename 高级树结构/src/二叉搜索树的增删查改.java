public class 二叉搜索树的增删查改 {
    //实现对一颗搜索二叉树的增删查改
    public class Node {
        public int value;
        public Node parent;
        public Node left;
        public Node right;

        public Node(int v, Node p, Node l, Node r) {
            value = v;
            parent = p;
            left = l;
            right = r;
        }
    }

    //写一颗二叉搜索树
    public class AbstractBinarySearchTree {
        public Node root;

        protected Node createNode(int value, Node parent, Node left, Node right) {
            return new Node(value, parent, left, right);
        }

        //在搜索二叉树中查找某一个节点
        public Node search(int element) {
            if (root == null) {
                return null;
            }
            Node cur = root;
            //还有机会找，并且没找到就继续找
            while (cur != null && cur.value != element) {
                if (cur.value > element) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return cur;
        }

        //将一个节点插入到搜索二叉树中去
        public Node Insert(int element) {
            if (root == null) {
                root = createNode(element, null, null, null);
                return root;
            }
            //root!=null
            Node insertParent = null;
            Node curNode = root;
            while (curNode != null) {
                //1:在走之前更新一次insertParent
                insertParent = curNode;
                if (curNode.value > element) {
                    curNode = curNode.left;
                } else {
                    curNode = curNode.right;
                }
            }
            //现在insertParent到位了
            //1:把节点new出来
            Node newNode = createNode(element, insertParent, null, null);
            if (insertParent.value > element) {
                insertParent.left = newNode;
            } else {
                insertParent.right = newNode;
            }
            return newNode;
        }

        //BST树的删除：逻辑较为复杂，对coding能力要求稍微高
        //删除键值为key的节点
        public void delete(int element) {
            //1:在BST中找到这个节点
            Node deleteNode = search(element);
            if (deleteNode != null) {
                delete(deleteNode);
            }
        }

        public void delete(Node deleteNode) {
            //分种情况讨论
            //1:要删除的节点没有左孩子，直接让右孩子代替它
            //2:要删除的节点没有右孩子，直接让左孩子代替它
            //3:要删除的节点既有左孩子又有右孩子:拿到右子树的最小节点代替你
            if (deleteNode != null) {
                if (deleteNode.left == null) {
                    transplant(deleteNode, deleteNode.right);
                } else if (deleteNode.right == null) {
                    transplant(deleteNode, deleteNode.left);
                } else {
                    //1:拿到右子树的最小节点
                    Node successorNode = getMiniNode(deleteNode.right);
                    //如果successorNode不是deleteNode的右节点,则完成successorNode的右指针设置
                    if (successorNode != deleteNode.right) {
                        transplant(successorNode, successorNode.right);
                        successorNode.right = deleteNode.right;
                        deleteNode.right.parent = successorNode;
                    }
                    //完成父指针和左指针的设置
                    transplant(deleteNode, successorNode);
                    successorNode.left = deleteNode.left;
                    deleteNode.left.parent = successorNode;
                }
            }
            deleteNode.left = null;
            deleteNode.right = null;
            deleteNode.parent = null;
        }

        public Node getMiniNode(Node node) {
            while (node != null && node.left != null) {
                node = node.left;
            }
            return node;
        }

        public void transplant(Node deleteNode, Node newNode) {
            //分为三种情况
            //1:deleteNode没有父亲
            //2:deleteNode是父亲的左孩子
            //3:deleteNode是父亲的右孩子
            if (deleteNode.parent == null) {
                this.root = newNode;
            } else if (deleteNode.parent.left == deleteNode) {
                deleteNode.parent.left = newNode;
            } else {
                deleteNode.parent.right = newNode;
            }
            if (newNode != null) {
                newNode.parent = deleteNode.parent;
            }
        }
    }

    public class AbstractSelfBalancingBinarySearchTree extends AbstractBinarySearchTree {

        /**
         * Rotate to the left.
         *
         * @param node Node on which to rotate.
         * @return Node that is in place of provided node after rotation.
         */
        //把以当前node为头的树进行左旋转并返回新的头
        protected Node rotateLeft(Node node) {
            Node temp = node.right;
            temp.parent = node.parent;
            temp.left = node;
            node.parent = temp;

            node.right = temp.left;
            if (node.right != null) {
                node.right.parent = node;
            }
            //设置新的头，让头的头指向它
            if (temp.parent != null) {
                //是父亲的左孩子
                if (node == temp.parent.left) {
                    temp.parent.left = temp;
                } else {
                    temp.parent.right = temp;
                }
            } else {
                this.root = temp;
            }
            return temp;
        }
/*        protected Node rotateLeft(Node node) {
            Node temp = node.right;
            temp.parent = node.parent;

            node.right = temp.left;
            if (node.right != null) {
                node.right.parent = node;
            }

            temp.left = node;
            node.parent = temp;

            // temp took over node's place so now its parent should point to temp
            if (temp.parent != null) {
                if (node == temp.parent.left) {
                    temp.parent.left = temp;
                } else {
                    temp.parent.right = temp;
                }
            } else {
                root = temp;
            }

            return temp;
        }*/

        /**
         * Rotate to the right.
         *
         * @param node Node on which to rotate.
         * @return Node that is in place of provided node after rotation.
         */
        protected Node rotateRight(Node node) {
            Node temp = node.left;
            temp.parent = node.parent;
            temp.right = node;
            node.parent = temp;

            node.left = temp.right;
            if (node.left != null) {
                node.left.parent = node;
            }
            //把新的头的头设置好
            if (temp.parent != null) {
                if (node == temp.parent.left) {
                    temp.parent.left = temp;
                } else {
                    temp.parent.right = temp;
                }
            } else {
                this.root = temp;
            }
            return temp;
        }
        /*protected Node rotateRight(Node node) {
            Node temp = node.left;
            temp.parent = node.parent;

            node.left = temp.right;
            if (node.left != null) {
                node.left.parent = node;
            }

            temp.right = node;
            node.parent = temp;

            // temp took over node's place so now its parent should point to temp
            if (temp.parent != null) {
                if (node == temp.parent.left) {
                    temp.parent.left = temp;
                } else {
                    temp.parent.right = temp;
                }
            } else {
                root = temp;
            }

            return temp;
        }*/

    }

    public class AVLNode extends Node {
        public int height;

        public AVLNode(int value, Node parent, Node left, Node right) {
            super(value, parent, left, right);
        }
    }

    public class AVLTree extends AbstractSelfBalancingBinarySearchTree {
        private void reBalance(AVLNode node) {
            while (node != null) {
                AVLNode parent = (AVLNode) node.parent;
                //判断以node为头的树是否平衡
                int leftHeight = node.left == null ? 0 : ((AVLNode) node.left).height;
                int rightHeight = node.right == null ? 0 : ((AVLNode) node.right).height;
                int nodeBalance = rightHeight - leftHeight;
                if (nodeBalance == 2) {
                    //右子树的右链比左链长，则是RR型
                    if (node.right.right != null && ((node.right.left == null) ? true :
                            ((AVLNode) node.right.right).height >
                                    ((AVLNode) node.right.left).height)) {
                        node = (AVLNode) rotateLeft(node);
                        break;
                    } else {
                        doubleRotateRightLeft(node);
                        break;
                    }
                } else if (nodeBalance == -2) {
                    if (node.left.left != null && ((node.left.right == null) ? true :
                            ((AVLNode) node.left.left).height > ((AVLNode) node.left.right
                            ).height)) {
                        node = (AVLNode) rotateRight(node);
                        break;
                    } else {
                        node = (AVLNode) doubleRotateLeftRight(node);
                        break;
                    }
                } else {
                    updateHeight(node);
                }
                node = (AVLNode) parent;
            }
        }

        private Node doubleRotateLeftRight(Node node) {
            node.left = rotateLeft(node.left);
            return rotateRight(node.right);
        }

        private Node doubleRotateRightLeft(Node node) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        //在不断建树的过程中不断更新树的高度
        private void updateHeight(AVLNode node) {
            int leftHeight = node.left == null ? 0 : ((AVLNode) node.left).height;
            int rightHeight = node.right == null ? 0 : ((AVLNode) node.right).height;
            node.height = 1 + Math.max(leftHeight, rightHeight);
        }

        private void insert(int element) {
            Node newNode = super.Insert(element);
            reBalance((AVLNode) newNode);
        }
    }
}