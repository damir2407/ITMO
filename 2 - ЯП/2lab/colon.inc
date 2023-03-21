%macro colon 2
    %%next_val: 
    %ifndef current
    dq 0
    %else
    dq current
    %endif     
    %define current %%next_val
    %2: db %1, 0                                
%endmacro
