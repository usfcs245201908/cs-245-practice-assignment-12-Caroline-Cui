public class Hashtable {

    hashNode[] hashArr;
    int size;
    int capacity = 5000;

    public Hashtable()
    {
        size = 0;
        hashArr = new hashNode[capacity];
    }

//    this function will return the index
//     where the value will store in the array
    private int hash(String key, int size)
    {
        return Math.abs(key.hashCode())%size;
    }

    //add elements into array
    public void put(String key, String val)
    {
        int index = hash(key,hashArr.length);
        hashNode tempNode = hashArr[index];
        while (tempNode != null)
        {
            if (tempNode.key.equals(key))
                break;
            tempNode = tempNode.next;
        }
        if (tempNode != null)
            tempNode.value = val;
        else
        {
            if (size >= hashArr.length*0.8)
            {
                resize();
                index = hash(key, hashArr.length);
            }
            hashNode newNode = new hashNode();
            newNode.key = key;
            newNode.value = val;
            newNode.next = hashArr[index];
            hashArr[index] = newNode;
            size++;
        }

    }


    //resize
    private void resize()
    {
        capacity = capacity*2;
        hashNode[] temp = hashArr;
        hashArr = new hashNode[capacity];
        size = 0;
        for (hashNode item : temp)
        {
            while (item != null)
            {
                put(item.key,item.value);
                item = item.next;
//                hashNode next = item.next;
//                int index = (Math.abs(item.key.hashCode())) % newArray.length;
//                item.next = newArray[index];
//                newArray[index] = item;
//                item = next;
            }
        }
//        hashArr = newArray;

    }

    public String get(String key)
    {
        int index = hash(key, hashArr.length);
        hashNode temp = hashArr[index];
        while (temp != null)
        {
            if (temp.key.equals(key))
            {
                return temp.value;
            }
            temp =temp.next;
        }
        return null;
    }

    //remove elements
    public String remove(String key)
    {
        int index = hash(key, hashArr.length);
        if (hashArr[index] == null)
            return null;
        if (hashArr[index].key.equals(key))
        {
            hashNode temp = hashArr[index];
            hashArr[index] = hashArr[index].next;
            size--;
            return temp.value;
        }
        hashNode p = hashArr[index];
        hashNode c = p.next;
        while (c != null && ! c.key.equals(key)) {
            c = c.next;
            p = c;
        }
        if (c != null) {
            hashNode temp1 = c;
            p.next = c.next;
            size--;
            return temp1.value;
        }
        return null;
    }


    //check if contain
    public boolean containsKey(String key)
    {
        int index = hash(key, hashArr.length);
        hashNode node = hashArr[index];
        while (node != null)
        {
            if (node.key.equals(key))
                return true;
            node = node.next;
        }
        return false;
    }

}
