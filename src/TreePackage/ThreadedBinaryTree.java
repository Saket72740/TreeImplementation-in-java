package TreePackage;

public class ThreadedBinaryTree {
    Node root = null;
    class Node{
        Node left,right;
        boolean lthread,rthread;
        int data;
        Node(int data){
            this.left = null;
            this.right = null;
            this.lthread = true;
            this.rthread = true;
            this.data = data;
        }
    }
    void insert(int data)
    {
        Node ptr = root;
        Node par = null;
        boolean found = false;
        while(ptr != null){
            if(ptr.data == data){
                found = true;
                return ;
            }
            par = ptr;
            if(ptr.data > data){
                if(ptr.lthread == false)
                    ptr = ptr.left;
                else
                    break;
            }
            else{
                if(ptr.rthread == false)
                    ptr = ptr.right;
                else
                    break;
            }
        }
        if(found){
            System.out.println("Duplicates are not allowed");
            return ;
        }
        if(par == null)
        {
            root = new Node(data);
        }
        else if(par.data > data){
            Node n = new Node(data);
            n.left = par.left;
            n.right = par;
            par.left = n;
            par.lthread = false;
        }
        else{
            Node n = new Node(data);
            n.left = par;
            n.right = par.right;
            par.right = n;
            par.rthread = false;
        }
    }
    void delete(int data)
    {
        Node ptr,par;
        boolean found = false;
        ptr = root;
        par = null;
        while(ptr != null)
        {
            if(data == ptr.data){
                found = true;
                break;
            }
            par = ptr;
            if(data < ptr.data){
                if(ptr.lthread == false)
                    ptr = ptr.left;
                else
                    break;
            }
            else{
                if(ptr.rthread == false)
                    ptr = ptr.right;
                else
                    break;
            }
        }
        if(found == false){
            System.out.println("Data not found in threaded binary tree");
        }
        else if(ptr.lthread == false && ptr.rthread == false)
            case_c(par,ptr);
        else if(ptr.lthread == false)
            case_b(par,ptr);
        else if(ptr.rthread == false)
            case_b(par,ptr);
        else
            case_a(par,ptr);
    }
    void case_a(Node par,Node ptr)
    {
        if(par == null) {
            root = null;
            return;
        }
        if(par.left == ptr){
            par.left = ptr.left;
            par.lthread = true;
        }
        if(par.right == ptr) {
            par.right = ptr.left;
            par.rthread = true;
        }
    }
    void case_b(Node par,Node ptr)
    {
        Node child;
        Node s,p;
        if(ptr.lthread == false)
            child = ptr.left;
        else
            child = ptr.right;
        if(par == null)
            root = child;
        else if(ptr == par.left)
            par.left = child;
        else
            par.right = child;
        s = in_succ(ptr);
        p = in_pred(ptr);
        if(ptr.lthread == false)
            p.right = s;
        else {
            if (ptr.rthread == false)
                s.left = p;
        }
    }
    void case_c(Node par,Node ptr)          // not right (it is wrong)
    {
        Node succ,parsucc;
        parsucc = ptr;
        succ = ptr.right;
        while(succ.left != null){
            parsucc = succ;
            succ = succ.left;
        }
//        System.out.println("gngblglb");
        ptr.data = succ.data;
        if(succ.lthread == true && succ.rthread == true)
            case_a(parsucc,succ);
        else
            case_b(parsucc,succ);
    }
    Node in_succ(Node ptr)
    {
        if(ptr.rthread == true)
            return ptr.right;
        else{
            ptr = ptr.right;
            while(ptr.lthread == false)
                ptr = ptr.left;
            return ptr;
        }
    }
    Node in_pred(Node ptr)
    {
        if(ptr.lthread == true)
            return ptr.left;
        else{
            ptr = ptr.left;
            while(ptr.rthread == false)
                ptr = ptr.right;
            return ptr;
        }
    }
    void inOrder()
    {
        Node ptr = root;
        if(ptr == null){
            System.out.println("Tree is empty");
            return ;
        }
        while(ptr.lthread == false)
            ptr = ptr.left;
        while(ptr != null){
            System.out.print(ptr.data + " ");
            ptr = in_succ(ptr);
        }
    }
    void preOrder()
    {
        Node ptr = root;
        if(root == null){
            System.out.println("Tree is empty");
            return ;
        }
        while(ptr != null){
            System.out.print(ptr.data + " ");
            if(ptr.lthread == false)
                ptr = ptr.left;
            else if(ptr.rthread == false)
                ptr = ptr.right;
            else{
                while(ptr != null && ptr.rthread == true)
                    ptr = ptr.right;
                if(ptr != null)
                    ptr = ptr.right;
            }
        }
    }

}