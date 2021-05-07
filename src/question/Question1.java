package question;

public class Question1 {

    public static class Node {
        public int value;
        public Node next = null; // 下一個 node

        public Node(int value) {
            this.value = value;
        }
    }

    public static class LinkedList {

        public Node root = null;

        public void add(int nodeVal) {
            // TODO: 完成此func
            if(root == null) {
                root = new Node(nodeVal);
            } else {
                // 替換 root
                Node rootV2 = root;
                while (rootV2.next != null) {
                    rootV2 = rootV2.next;
                }
                rootV2.next = new Node(nodeVal);
            }
        }

        public void insert(int position, int value) {
            Node toInsert = toNode(position); // 找到即將要插入的node

            if(root == toInsert) {
                // Create a new node
                Node newNode = new Node(value); // 創建全新node
                newNode.next = root;
                root = newNode;
            } else {
                Node prev = null;

                // 方法一
                // for(
                //        Node nextNode = root;    // 創建全新的 node
                //        nextNode != toInsert;    // 當前node != position找到node
                //        prev = nextNode,         // 賦予新的node (前)
                //        nextNode = nextNode.next // 賦予新的node.next（後）
                // );

                // 方法二
                //ex: 當 toInsert = C, A B C
                Node nextNode = root;           // 創建全新的 node (B)
                while( nextNode != toInsert ){  // 當前node != position找到node
                    prev = nextNode;            // 賦予新的node (前 B)
                    nextNode = nextNode.next;   // 賦予新的node.next（後 C）
                }
                // Create a new node
                Node newNode = new Node(value);

                // Update the m.next
                newNode.next = prev.next;       // XX.next = B.next

                // Update previous node's next
                prev.next = newNode;            // B.next = XX
                                                // A B XX C

            }
        }

        // 從頭插入
        public void push(int nodeVal) {
            Node node = new Node(nodeVal);
            node.next = root;
            root = node;
        }

        //最後插入
        public void insertAfter(int nodeVal) {
            if (root == null) {
                System.out.println("node 不能為空");
                return;
            }

            Node newNode = new Node(nodeVal); // 取得新value
            Node rootV2 = root;

//            System.out.println("rootV2:" + rootV2.value);

            while (rootV2.next != null) { // 一直賦值到 next = null
                rootV2 = rootV2.next;
            }
            rootV2.next = newNode; // next = null，就將next = newNode
        }

        public boolean remove(int val) {
            // TODO: 完成此func
            Node node = root;
            Node before = null;

            // 當 val == 1
            if(root != null && root.value == val){ // root 不等於 null, root.value 等於 當前的value
                root = root.next; // 直接root指向下一個root(等於是把當前的刪除)
                return true;
            }

            // 當 val > 1
            while(node != null && node.next != null){ // node 有資料 並 node.next 有資料
                // find before node
                // 說明刪除的 val >= 2
                if(node.next.value == val){
                    before = node;  // 當node=2,node.next=3,就將node賦值給before
                                    // 原因是當val=3時,要保留2,並刪除3
                    break;          // 跳出此迴圈
                }
                node = node.next; // 一直賦值到 next = null
            }

            // 刪除當前 val
            // 原因是當val=3時,上面已經保留2, 2的下一個就是3,讓3 = 4, 就會變成 1,2,4
            // 3這樣就會被刪除
            if(before != null){
                before.next = before.next.next;
                return true;
            }
            return false;
        }

        // 找key對應到的Node
        private Node toNode(int local) {
            Node toN = root;
            for (int i = 0; i<local;i++) {
                toN = toN.next;
                //System.out.println("no");
            }
            return toN;
        }

        // 找 NODE
        public Node find(int value) {
            // TODO: 完成此func
            Node node = root;
            while(node != null) {
                if(value == node.value){
                    return node;
                }
                node = node.next;
            }
            return node;
        }

        public int size() {
            // TODO: 完成此func
            Node node = root;
            int count = 0;
            while(node != null) {
//                System.out.println(node.value);
                count ++;
                node = node.next;
            }
            return count;
        }

        public String toString() {
            if (root == null) {
                return "";
            }

            StringBuilder sb = new StringBuilder();
            Node node = root;
            while (node != null) {
                sb.append(node.value + "->");
                node = node.next;
            }
            sb.append("null");

            return sb.toString();
        }

    }

    public static void main(String[] args) {
        LinkedList listData = new LinkedList();

        listData.add(5);
        listData.add(4);
        listData.add(3);
        listData.add(2);
        listData.add(1);

        listData.remove(3); // 刪除

        int size = listData.size(); // 長度

        Node findNode = listData.find(2); // 查找node

        listData.insert(1, 99); // 節點插入

        listData.push(88); // 從頭插入

        listData.insertAfter(77); // 從最後插入

        System.out.println("LinkedList:" + listData.toString());
        System.out.println("size:" + size);
        System.out.println("findNum:" + findNode.value);

    }

}
