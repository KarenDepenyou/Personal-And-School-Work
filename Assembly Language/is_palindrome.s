#Julie Depenyou UID: 115911437 login: depenyou 
strlen: 
	   # PROLOGUE 
	        subu $sp, $sp, 8        # expand stack by 8 bytes 
	        sw   $ra, 8($sp)        # push $ra (ret addr, 4 bytes) 
	        sw   $fp, 4($sp)        # push $fp (4 bytes) 
	        addu $fp, $sp, 8        # set $fp to saved $ra 
	   # BODY: 
	      li $t0, 0 
	 
	 while: 
	        lb $t1, 0($a0) 
	        beqz $t1, exit 
	#       add $v0, $v0, 1 
	        add $a0, $a0, 1 
	        add $t0, $t0, 1 
	        j while 
	 
	 
	# EPILOGUE
	 exit: 
	       # jr $ra 
	        add $v0, $zero, $t0 
	        move $sp, $fp           # restore $sp 
	        lw   $ra, ($fp)         # restore saved $ra 
	        lw   $fp, -4($sp)       # restore saved $fp 
	        jr   $ra                # return to kernel 
	#       jr $ra 
	 
	# EPILOGUE
is_palindrome: 
	        subu $sp, $sp, 8        # expand stack by 8 bytes 
	        sw   $ra, 8($sp)        # push $ra (ret addr, 4 bytes) 
	        sw   $fp, 4($sp)        # push $fp (4 bytes) 
	        addu $fp, $sp, 8        # set $fp to saved $ra 
	 
	        addi $s0, $0, 0         #i = 0 
	        add $t0, $0, 0          #t0 = len 

		subu $sp, $sp, 4        #expand by 4 more
	        sw $a0, 4($sp)          #save word 
	        jal strlen              #get len of str 
	 
	        lw $a0, 4($sp)          #restore str 
	        #sw $v0, 8($sp)         #save strlen(str) 
	 
	        add $t0, $v0, 0         #len = strlen(str) 
	         
	 
	 
forloop:

		div $s1, $t0, 2        #s1 = len/2
	 	slt $t1, $s0, $s1      #set to 1 if i < len/2 
	        beq $t1, $0, end       #exit if equals 0 
	        add $t1, $a0,$s0       #address = img [0] + i 
	        lbu $t2, 0($t1)        #t2 = str[i] 
	        sub $s2, $t0, $s0      #s2 = len - i 
	        sub $s2, $s2, 1        #s2 = s1 - 1 
	        add $t3, $a0, $s2      #address = img[0] + s2 
	        lbu $t4, 0($t3)        #t4 = str[len-i-1] 
	        beq $t4, $t2, forend   # branch if they're equal 

		addi $s3, $0, 0        #s3 = 0
      		move $v0, $s3          #return 0
	        j return_1 
	         
	         
forend: 
	        addi $s0, $s0, 1         #i++ 
		j forloop

return_1:
 		addi $s4, $0, 1         #s4 = 1
		move $v0, $s4           #return 1
end:
	        move $sp, $fp           # restore $sp 
	        lw   $ra, ($fp)         # restore saved $ra 
	        lw   $fp, -4($sp)       # restore saved $fp 
	        jr   $ra                # return to kernel 
	         
