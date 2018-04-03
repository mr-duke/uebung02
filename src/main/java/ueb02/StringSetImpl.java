package ueb02;

import java.util.NoSuchElementException;

public class StringSetImpl implements StringSet {

    class Element {
        String value;
        Element left, right;

        Element (String value, Element left, Element right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
        int size(){
            int s = 1;

            if(this.left != null)
                s += this.left.size();
            if (this.right != null)
                s += this.right.size();

            return s;
        }
    }

    private Element root = null;

    @Override
    public boolean add(String s) {
        Element ele = new Element(s, null, null);

        if (root == null)
            root = ele;

        Element it = root;

        while(it != null ){
            if (it.value.compareTo(ele.value) == 0)
                return false;
            else if (it.value.compareTo(ele.value) < 0) {
                if(it.left == null) {
                    it.left = ele;
                    return true;
                }
                else
                    it = it.left;
            }
            else
                if(it.right == null) {
                    it.right = ele;
                    return true;
                }
                else
                    it = it.right;
        }
        return false;
    }

    @Override
    public boolean contains(String s) {
        if (root == null)
            return false;

        Element it = root;

        while (it !=  null) {
            if (it.value.compareTo(s) == 0)
                return true;
            else if (it.value.compareTo(s) < 0){
                it = it.left;
            }
            else {
                it = it.right;
            }
        }
        return false;
    }

    @Override
    public String remove(String s) {
        if (root == null)
            throw new NoSuchElementException();

        if (root.value.compareTo(s) == 0)
            return removeRoot();

        Element it = root;
        while (it != null) {
            if(it.value.compareTo(s) < 0){
                if (it.left != null && it.left.value.compareTo(s) == 0)
                    return removeElement(it, it.left);
                it = it.left;
            }
            else
                if(it.right != null && it.right.value.compareTo(s) == 0)
                    return removeElement(it, it.right);
                it = it.right;
        }
        throw new NoSuchElementException();
    }

    private String removeRoot(){
        Element ele = root;
        if(ele.left == null && ele.right == null){
            root = null;
            return ele.value;
        }
        else if (ele.left == null){
            root = ele.right;
            return ele.value;
        }
        else if (ele.right == null){
            root = ele.left;
            return ele.value;
        }
        else {
            root = ele.left;
            addElement(ele.right);
            return ele.value;
        }
    }

    private String removeElement(Element parent, Element element){
        if (parent.left == element){
            parent.left = null;
        }
        else {
            parent.right = null;
        }
        addElement(element.left);
        addElement(element.right);

        return element.value;
    }

    private void addElement(Element e){
        if (e == null)
            return;
        if (root == null)
            root = e;

        Element it = root;
        while (it != null){
            if (it.value.compareTo(e.value) == 0)
                return;
            else if (it.value.compareTo(e.value) < 0){
                if (it.left == null) {
                    it.left = e;
                    return;
                }
                else
                    it = it.left;
            }
            else{
                if (it.right == null){
                    it.right = e;
                    return;
                }
                else
                    it = it.right;
            }
        }
    }

    @Override
    public int size() {
        if (root == null)
            return 0;
        else
            return root.size();
    }
}
