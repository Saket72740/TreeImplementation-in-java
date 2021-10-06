package TreePackage;
import java.util.*;

public class BinarySearchTree {
    Node root = null;
    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    void insert(int data)
    {
        Node ptr,par;
        ptr = root;
        par = null;
        while(ptr != null){
            par = ptr;
            if(ptr.data == data){
                System.out.println("Duplicate keys are not allowed");
                return;
            }
            else if(ptr.data > data)
                ptr = ptr.left;
            else
                ptr = ptr.right;
        }
        if(par == null)
            root = new Node(data);
        else if(data < par.data)
            par.left = new Node(data);
        else
            par.right = new Node(data);
    }
    void delete(int data){
        Node par,ptr;
        ptr = root;
        par = null;
        while(ptr != null){
            if(data == ptr.data)
                break;
            par = ptr;
            if(data < ptr.data)
                ptr = ptr.left;
            else
                ptr = ptr.right;
        }
        if(ptr == null)
            System.out.println("Data is not found in tree");
        else if(ptr.right != null && ptr.left != null)
            case_c(par,ptr);
        else if(ptr.left != null)
            case_b(par,ptr);
        else if(ptr.right != null)
            case_b(par,ptr);
        else
            case_a(par,ptr);
    }
    private void case_a(Node par,Node ptr){
        if(par == null)
            root = null;
        else if(ptr == par.left)
            par.left = null;
        else
            par.right = null;
    }
    private void case_b(Node par,Node ptr){
        Node child;
        if(ptr.left != null)
            child = ptr.left;
        else
            child = ptr.right;
        if(par == null)
            root = child;
        else if(ptr == par.left)
            par.left = child;
        else
            par.right = child;
    }
    private void case_c(Node par,Node ptr){
        Node succ,parsucc;
        parsucc = ptr;
        succ = ptr.right;
        while(succ.left != null){
            parsucc = succ;
            succ = succ.left;
        }
        ptr.data = succ.data;
        if(succ.left == null && succ.right == null)
            case_a(parsucc,succ);
        else
            case_b(parsucc,succ);
    }
    void inOrder()                                  // non-recursive way (using stack)
    {
        Node ptr = root;
        if(ptr == null) {
            System.out.println("Tree is empty");
            return;
        }
        Stack<Node> stk = new Stack<>();
        while(true){
            while(ptr.left != null){
                stk.push(ptr);
                ptr = ptr.left;
            }
            while(ptr.right == null){
                System.out.print(ptr.data + " ");
                if(stk.isEmpty()) {
                    System.out.println();
                    return ;
                }
                ptr = stk.pop();
            }
            System.out.print(ptr.data + " ");
            ptr = ptr.right;
        }
    }
    void inOrder(Node root){                        // recursive way
        if(root == null)
            return ;
        inOrder(root.left);
        System.out.println(root.data + " ");
        inOrder(root.right);
    }
    void preOrder(){                                // non-recursive way (using stack)
        Node ptr = root;
        Stack<Node> stk = new Stack<>();
        if(ptr == null){
            System.out.println("Tree is empty");
            return ;
        }
        stk.push(ptr);
        while(!stk.isEmpty()){
            ptr = stk.pop();
            System.out.print(ptr.data + " ");
            if(ptr.right != null)
                stk.push(ptr.right);
            if(ptr.left != null)
                stk.push(ptr.left);
        }
        System.out.println();
    }
    void preOrder(Node root){                   // recursive way
        if(root == null)
            return ;
        System.out.println(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
    void postOrder(){                           // non-recursive way (using stack)
        Node ptr = root;
        Stack<Node> stk = new Stack<>();
        if(ptr == null){
            System.out.println("Tree is empty");
            return ;
        }
        Node q = root;
        while(true){
            while(ptr.left != null){
                stk.push(ptr);
                ptr = ptr.left;
            }
            while(ptr.right == null || ptr.right == q){
                System.out.print(ptr.data + " ");
                q = ptr;
                if(stk.isEmpty()){
                    System.out.println();
                    return ;
                }
                ptr = stk.pop();
            }
            stk.push(ptr);
            ptr = ptr.right;
        }
    }
    void postOrder(Node root){                  // recursive way
        if(root == null)
            return ;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data + " ");
    }
    void levelOrder(){                          // non-recursive way (using queue)
        Queue<Node> q = new LinkedList<>();
        Node ptr = root;
        if(root == null){
            System.out.println("Tree is empty");
            return ;
        }
        q.add(ptr);
        while(!q.isEmpty()){
            ptr = q.remove();
            System.out.print(ptr.data + " ");
            if(ptr.left != null)
                q.add(ptr.left);
            if(ptr.right != null)
                q.add(ptr.right);
        }
        System.out.println();
    }
    void search(int data)
    {
        Node ptr = root;
        if(root == null){
            System.out.println("Tree is empty");
            return ;
        }
        while(ptr != null){
            if(ptr.data == data){
                System.out.println(ptr.data + " found in tree");
                return ;
            }
            else if(ptr.data > data)
                ptr = ptr.left;
            else
                ptr = ptr.right;
        }
        System.out.println(data + " not found in tree");
        return ;
    }
    int height(Node ptr)                    // recursive way
    {
        int h_left,h_right;
        if(ptr == null)
            return 0;
        h_left = height(ptr.left);
        h_right = height(ptr.right);
        if(h_left > h_right)
            return 1+h_left;
        else
            return 1+h_right;
    }
    int min()
    {
        Node ptr = root;
        while(ptr.left != null)
            ptr = ptr.left;
        return ptr.data;
    }
    int max()
    {
        Node ptr = root;
        while(ptr.right != null)
            ptr = ptr.right;
        return ptr.data;
    }
}
