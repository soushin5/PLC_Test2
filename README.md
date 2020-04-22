q1) the program using enums is more readable as you can tell what the variables are for. In terms of reliability, the integer from is       
    better since you dont have to cast the enum into an integer or using it within another function.

q2) Perl dynamic scoping is based on where you declare the local varibale. The Dynamic scoping explained in the chapter is
    based on the order in which the subprograms are called.

q3) The static array took the least amount of time because everything about it was created at compile time. The fixed Heap dynamic array       took the longest because it had to create everything at runtime and then delete it as well before starting the next creation.
    
    The reason you can't do this in Java is becasue Java creates all arrays as fixed heap dynamic arrays.
 
q4) 

q5) sum1: (i / 2) + fun(&i)
       ~: i / 2 -> 10 / 2 = 5
       
       ~: fun(&i) -> take the address of i and give it to fun()
       
       ~: fun(int *k) -> take the value from the pointer at the address passed to k -> 10
       
       ~: *k += 4; -> 10 += 4; -> *k = 14 the value at the address in &i is now different
       
       ~: return 3 * (*k) - 1; -> return 3 * (14) - 1; -> return 41
       
       ~: (i / 2) + fun(&i); -> 5 + 41 -> 46
    
    sum2: fun(&j) + (j / 2)
       
       ~: fun(&j) -> take the address of j and give it to fun()
       
       ~: fun(int *k) -> take the value from the pointer at the address passed to k -> 10
       
       ~: *k += 4; -> 10 += 4; -> 14
       
       ~: return 3 * (*k) - 1; -> return 3 * (14) - 1; -> return 41
       
       ~: j / 2 -> 14 / 2 = 7
       
       ~: fun(&j) + (j / 2); -> 41 + 7 -> 48
    
    w/out precedence 
       
       ~: sum1 = 46, order is the same with or without precedence
       
       ~: sum2 = 24, without precedence the order does matter


q6) Dynamic Scoping
   
       a. sub3: a,x,w sub2: b,z sub1: y
       
       b. sub3: a,x,w sub1: y,z 
       
       c. sub1: a,y,z sub3: x,w sub2: b
       
       d. sub1: a,y,z sub3: x,w 
       
       e. sub2: a,b,z sub3: x,w sub1: y
       
       f. sub1: a,y,z sub2: b sub3: x,w
    
    Static Scoping
      
       a. sub3: a,x,w
       
       b. sub3: a,x,w  
       
       c. sub1: a,y,z 
       
       d. sub1: a,y,z  
       
       e. sub2: a,b,z 
       
       f. sub1: a,y,z

q7) a>b && b>c is the mathematics way of reading a>b>c
    In a C language, it would evaluate to 0 or 1 depending on if a>b or not
    the two do not mean the same thing.
    
q8) Subscripting:
       
       a. ((a*b)1 - (1+c)2)3
       
       b. ((((++a)2 * (b - 1)1)3 / c)4 % d)5
       
       c. ((a - b)1 / (c & (((d * e)2 / a)3 - 3)4)5)6
       
       d. ((-a)1 or ((c = d)2 and e)3)4
       
       e. (((a > b)1 xor c)3 or (d <= 17)2)4
    
    Right to left Associativity
       
       a. c + 1 - b * a
       
       b. d % c / a++ * 1 - b          (does a unary operator need to be first?)
       
       c. (a-b) / c & 3 - a / e * d  ;  you can't rewrite this without parenthesis because a-b cant be done fist without using                                                   parenthesis
       
       d.  -a or e and d = c           (does - need to be on the right side for it to be negative still?)
       
       e.  b < a                     ; this statement needs to have ((d<=17)xor c) be done first but it cant be written without a                                              parenthesis or it will be used with d 

q9) <unary_expr> -> (++ | --)
    <expr> -> <term> {}
       
