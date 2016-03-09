/*
  an SList is an object that holds a bunch of Strings (for now)
  in some order
*/

public class SList
{
  // instance variables:
  private String[] items;  // the array that holds the items in the list
  private int n;  // the current number of items in the list

  // constructors:

  // construct an empty list with space in the array for 
  // initCapacity items
  public SList( int initCapacity )
  {
    items = new String[ initCapacity ];
    n = 0;
  }

  // instance methods:

  // append the item s to the end of the list
  public void append( String s )
  {
    makeMoreSpaceIfNecessary();

    items[ n ] = s;
    n++;
  }

  // insert the item s into the list at index,
  // shift the others
  public void insert( int index, String s )
  {
    if( index < 0 )
    {
      System.out.println("illegal index---smaller than 0");
      System.exit(1);
    }
    else if( index > n )
    {
      System.out.println("illegal index---greater than the size");
      System.exit(1);
    }
    else
    {
      makeMoreSpaceIfNecessary();

      // shift everybody from the target location upward
      for( int k=n-1; k>=index; k-- )
      {
        items[ k+1 ] = items[ k  ];
      }

      items[ index ] = s;

      n++;
    
    }

  }

  // double the space and restore the instance variables
  private void makeMoreSpaceIfNecessary()
  {
    if( n == items.length )
    {
      // make a new, bigger array
      String[] temp = new String[ items.length*2 ];

      // copy the existing items to new array
      for( int k=0; k<n; k++ )
      {
        temp[ k ] = items[ k ];
      }

      // make items point to the new array
      items = temp;
    }

  }

  // remove all the items (capacity stays same)
  public void clear()
  {
    n = 0;
  }

  // return the item in the given position
  public String get( int index )
  {
    if( index < 0 || index >= n )
      return null;
    else
      return items[ index ];
  }

  // remove the item in the given position
  public void remove( int index )
  {
    if( index<0 || n<=index )
    {
      System.out.println("Illegal index in remove");
      System.exit(1);
    }
    else
    {
      for(int k=index+1; k<n; k++ )
      {
        items[ k-1 ] = items[ k ];
      }

      n--;
    }

  }

  // replace the item in given position by the given item
  public void replace( int index, String s )
  {
    if( index < 0 || index>=n ) 
    {
      System.out.println("no such item to replace");
      System.exit(1);
    }
    else
    {
      items[ index ] = s;
    }
  }

  public int size()
  {
    return n;  
  }

  // seems inefficient!
  public String toString()
  {
    String temp = "[";
    for(int k=0; k<n; k++ )
    {
      temp += items[k]; // or could do temp += get(k)
      if( k<n-1 )
        temp += ",";
    }
    temp += "]";
    return temp;
  }

  public void display()
  {
    for(int k=0; k<n; k++ )
      System.out.print( items[k] + "," );
  }

  public static void main(String[] args)
  {
    SList x = new SList( 4 );

    x.append( "a" );
    x.append( "b" );
    x.append( "c" );
    x.append( "d" );
    x.append( "e" );

    System.out.println("[a,b,c,d,e]? " +  x );

    x.insert( 0, "x" );
    x.insert( 2, "y" );
    x.insert( 6, "z" );
    x.insert( 8, "A" );

    System.out.println("[x,a,y,b,c,d,z,e,A]? " +  x );
    
    x.remove( 0 );
    x.remove( 3 );
    x.remove( x.size()-1 );

    System.out.println("[a,y,b,d,z,e]? " +  x );
    
    for( int k=0; k<x.size(); k++ )
      System.out.println( x.get(k) );

    for( int k=-20; k<x.size()+20; k++ )
      System.out.println( x.get(k) );

    x.clear();

    System.out.println("[]? " +  x );

    for( int k=1; k<=100000; k++ )
      x.append( "" + k );

    
    System.out.println("#'s from 1 to 100000?\n");
    x.display();

  }

}
