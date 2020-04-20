$x = 10; 
sub f {  
   return $x;  
} 
sub g {  
   local $x = 20;  
  
   return f();  
} 
sub d {
    my $x = 15;
    return f();
}
print g()."\n"; 
print d()."\n"; 