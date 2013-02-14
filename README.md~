secdroid
========

Secure your device before it becomes someone else's device

SecDroid hardens the android kernel by disable certain binaries that have internet access or can be used as an attack vector and by securing the TCP Stack using Systctl. This helps prevent some MITM (Man-In-The-Middle) and Spoofing attacks. It may also prevent some DOS (Denial-Of-Service) Attacks. 

NOTE: ROOT IS NEEDED!


Binary's that are disabled:

* irsii
* nano
* nc (net cat)
* netserver
* netperf
* opcontrol
* scp
* rsync
* sdptest
* ssh
* sshd
* strace
* tcpdump
* vim
* ping (Changed so only root can use)

* pm (package manager) is disabled so install apps via adb is disabled
* adbd (adb daemon) is disable as well to prevent an attacker from using tools such as p2p-adb by Kos

This is a work in progress so please let me know what you want added, removed, changed, or fixed.
