#!/bin/bash

SCRIPT_PATH=$0
NEED_TO_REBOOT=false

if ! [ -x $SCRIPT_PATH ];
then
 echo Making script executable, please, enter password if needed!
 sudo chmod +x $SCRIPT_PATH
 echo Script is executable now.
fi


grep -qcE "^admin:" /etc/group

check_groups(){
 if ! (grep -qcE "^$1:" /etc/group); then
  echo "Warning: group $1 does not exist"
  return
 fi

 if (id -Gn $USER | grep -cq $1); then
  echo "Group $1 is set"
  return
 fi

 echo "Group $1 is not set"
 sudo usermod -a -G $1 $USER
 NEED_TO_REBOOT=true
 echo "Group $1 is updated"
}

check_groups uucp
check_groups dialout
# check_groups lock
check_groups tty

if $NEED_TO_REBOOT; then
 echo "Please reboot your system before next run"
 exit
fi

echo Starting script

bellsoft-java17-aarch64/bin/java -jar com.example.order-linux-x64-1.0.0.jar