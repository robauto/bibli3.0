/**
 * purpose- arrange list and get a list from a category
 * Created by pi on 5/27/16.
 */
public class TestContact
{
    public static void main(String[] args)
    {
        int test = 0;
        Contact[] myContacts = new Contact[6];
        myContacts[0] = new Contact("John Carter","brother","Mar 3","(342)555-7069","jcarter@carter.com");
        myContacts[1] = new Contact("Elise Carter","mom","Apr 19","(342)555-7011","carterMom@carter.com");
        myContacts[2] = new Contact("Ellie Carter","me","Jun 10","(342)555-8102","ecarter@carter.com");
        myContacts[3] = new Contact("Sue Ellen","friend","Mar 9","(341)555-9182","susieE@hotmail.com");
        myContacts[4] = new Contact("Frank Carter","dad","Dec 1","(342)555-7011","carterDad@carter.com");
        myContacts[5] = new Contact("Johnnie","friend","Jan 21","(341)555-7789","jDawg5555@yahoo.com");
        
        
        System.out.println("              Contact List");
        System.out.println();
        printContacts(myContacts);
        //search names in  Contact List
        System.out.println("Find Name - Johnnie");
        sortNames(myContacts);
        test = findByName(myContacts, "Johnnie");
        if (test != -1)
        {
            System.out.println("Found:" + myContacts[test].toString());
        }
        else
        {   
            System.out.println("Not found.");
        }
        System.out.println();
        System.out.println("Find Name - Sam Parker");
        sortNames(myContacts);
        test = findByName(myContacts, "Sam Parker");
        if (test != -1)
        {
            System.out.println("Found:" + myContacts[test].toString());
        }
        else
        {   
            System.out.println("Not found.");
        }
        //search relations
        System.out.println();
        findByRelation(myContacts,"friend");
        findByRelation(myContacts,"Aunt");
        
        //search phones
        findByPhone(myContacts,"(333)555-8989");
        findByPhone(myContacts,"(342)555-7011");
        
        //search birthdays
        findByBMonth(myContacts,"May");
        findByBMonth(myContacts,"Mar");
        
        //search emails
        System.out.println("Find Email - rgoodman@hotmail.com");
        myContacts = sortEmail(myContacts);
        test = findByEmail(myContacts, "rgoodman@hotmail.com");
        if (test != -1)
        {
            System.out.println("Found:" + myContacts[test].toString());
        }
        else
        {   
            System.out.println("Not found.");
        }
        System.out.println();System.out.println("Find Email - susieE@hotmail.com");
        myContacts = sortEmail(myContacts);
        test = findByEmail(myContacts, "susieE@hotmail.com");
        if (test != -1)
        {
            System.out.println("Found:" + myContacts[test].toString());
        }
        else
        {   
            System.out.println("Not found.");
        }
    }
    //prints the arraylist
    public static void printContacts(Contact[] c)
    {
        System.out.println("Name       Relation    Birthday    Phone         Email");
        System.out.println("---------------------------------------------------------");
        for(int i = 0; i < c.length; i++)
        {
            System.out.println(c[i]);
        }
        System.out.println();
    }
    //insertion sorts the arraylist by names
    public static Contact[] sortNames(Contact[] c)
    {
        Contact[] newlist = new Contact[c.length];
        for(int i=0;i<c.length;i++)
        {
            String next = c[i].getName();
            int insert = 0;
            int k =i;
            while(k>0 && insert == 0)
            {
                if(next.compareTo( newlist[k-1].getName() ) > 0)
                {
                    insert = k;
                }
                else
                {
                    newlist[k] = newlist[k-1];
                }
                k--;
            }
            newlist[insert]=c[i];
        }
        return newlist;
    }
    //binary search for a name
    public static int findByName(Contact[] c1, String toSearch )
    {
        int high = c1.length;
        int low = -1;
        int pos;
        while ( high - low > 1 )
        {
            pos = ( high + low ) / 2;
            if ( c1[pos].getName().compareTo(toSearch) > 0)
            {    
                high = pos;
            }
            else
            {
                low = pos;
            }
        }
        if ( (low >= 0) && (c1[low].getName().compareTo(toSearch) == 0 ))
        {
            return low;
        }
        else
        {
            return -1;
        }
    }
    //find people by the relation
    public static void findByRelation(Contact[] c1, String toSearch)
    {
        int found = 0;
        System.out.println("Find Relation - " + toSearch);
        System.out.println("Find Results:");
        for(int i = 0; i < c1.length; i++)
        {
            if (c1[i].getRelation().compareTo(toSearch) == 0)
            {
                System.out.println("Found: " + c1[i].toString());
                found++;
            }
        }
        if (found == 0)
        {
            System.out.println("There are no listings for " + toSearch);
        }
        else
        {
            System.out.println("There were " + found + " listings for " + toSearch);
        }
        System.out.println();
    }
    //find people by in which  month they were born in
    public static void findByBMonth(Contact[] c2, String toSearch)
    {
        int found = 0;
        System.out.println("Find Bday - " + toSearch);
        System.out.println("Find Results:");
        for(int i = 0; i < c2.length; i++)
        {
            if (c2[i].getBday().contains(toSearch))
            {
                System.out.println("Found: " + c2[i].toString());
                found++;
            }
        }
        if (found == 0)
        {
            System.out.println("There are no listings for " + toSearch);
        }
        else
        {
            System.out.println("There were " + found + " listings for " + toSearch);
        }
        System.out.println();
    }
    //find people by their phone number
    public static void findByPhone(Contact[] c3, String toSearch)
    {
        int found = 0;
        System.out.println("Find Phone - " + toSearch);
        System.out.println("Find Results:");
        for(int i = 0; i < c3.length; i++)
        {
            if (c3[i].getPhoneNumber().compareTo(toSearch) == 0)
            {
                System.out.println("Found: " + c3[i].toString());
                found++;
            }
        }
        if (found == 0)
        {
            System.out.println("There are no listings for " + toSearch);
        }
        else
        {
            System.out.println("There were " + found + " listings for " + toSearch);
        }
        System.out.println();
    }
    //insertion sorts the arraylist by email
    public static Contact[] sortEmail(Contact[] c4)
    {
        Contact[] newlist = new Contact[c4.length];
        for(int i=0;i<c4.length;i++)
        {
            String next = c4[i].getEmailAddress();
            int insert = 0;
            int k =i;
            while(k>0 && insert == 0)
            {
                if(next.compareTo( newlist[k-1].getEmailAddress() ) > 0)
                {
                    insert = k;
                }
                else
                {
                    newlist[k] = newlist[k-1];
                }
                k--;
            }
            newlist[insert]=c4[i];
        }
        return newlist;
    }
    //searches emails
    public static int findByEmail(Contact[] c5, String toSearch )
    {
        int high = c5.length;
        int low = -1;
        int pos;
        while ( high - low > 1 )
        {
            pos = ( high + low ) / 2;
            if ( c5[pos].getEmailAddress().compareTo(toSearch) > 0)
            {    
                high = pos;
            }
            else
            {
                low = pos;
            }
        }
        if ( (low >= 0) && (c5[low].getEmailAddress().compareTo(toSearch) == 0 ))
        {
            return low;
        }
        else
        {
            return -1;
        }
    }
}