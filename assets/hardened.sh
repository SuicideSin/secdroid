#!/system/bin/sh
# 
# January 2013 
#Copyright (C) 2013  x942 AKA Kyle Davidson

#This program is free software; you can redistribute it and/or
#modify it under the terms of the GNU General Public License
#as published by the Free Software Foundation; either version 2
#of the License, or (at your option) any later version.
#
#This program is distributed in the hope that it will be useful,
#but WITHOUT ANY WARRANTY; without even the implied warranty of
#MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#GNU General Public License for more details.
#
#You should have received a copy of the GNU General Public License
#along with this program; if not, write to the Free Software
#Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
#
mount -o rw,remount /system
# Disable Bluetooth
# Disabled as it only works on Nexus 4
#chmod 000 /dev/bcm2079x-i2c
#chmod 000 /dev/smd2
#chmod 000 /dev/smd3
# stop market if running
#Disabled as most users don't wont this.
#killall com.android.vending
# hardening TCP/IP stack for IPV4
sysctl -w net.ipv4.icmp_echo_ignore_broadcasts=1 #ICMP broadcast
sysctl -w net.ipv4.conf.all.accept_redirects=0 # ICMP redirects ipv4
sysctl -w net.ipv6.conf.all.accept_redirects=0 #ICMP redirects ipv6
sysctl -w net.ipv4.conf.all.send_redirects=0 # ICMP redirects
sysctl -w net.ipv4.conf.all.accept_source_route=0 #source routing disable
sysctl -w net.ipv4.conf.all.forwarding=0 #Forwarding traffic
sysctl -w net.ipv4.conf.all.rp_filter=1
sysctl -w net.ipv4.conf.all.log_martians=1 #filter martians
sysctl -w net.ipv4.tcp_max_syn_backlog=1280 # TCP syn half-opened
sysctl -w net.ipv4.ip_forward=0
# Removing/ disabling unnecessary binaries. Some of them have access to Internet
echo "If the script reports 'unable to chmod' that means you are safe and the file(s) are not present"
echo "Please let me know if your rom places these files elsewhere"
chmod 000 /system/xbin/irsii
chmod 000 /system/xbin/nc
chmod 000 /system/xbin/netserver
chmod 000 /system/xbin/netperf
chmod 000 /system/xbin/opcontrol
chmod 000 /system/xbin/scp
chmod 740 /system/xbin/rsync
chmod 740 /system/xbin/sdptest
chmod 000 /system/xbin/ssh
chmod 000 /system/xbin/sshd
chmod 000 /system/xbin/ssh-keygen
chmod 740 /system/xbin/strace
chmod 000 /system/xbin/tcpdump
chmod 740 /system/xbin/vim
chmod 000 /system/xbin/nano
# Let's make sure they aren't in bin either
# Can we make this cleaner? Maybe with IF statements?
chmod 000 /system/bin/irsii
chmod 000 /system/bin/nc
chmod 000 /system/bin/netserver
chmod 000 /system/bin/netperf
chmod 000 /system/bin/opcontrol
chmod 000 /system/bin/scp
chmod 740 /system/bin/rsync
chmod 740 /system/bin/sdptest
chmod 000 /system/bin/ssh
chmod 000 /system/bin/sshd
chmod 000 /system/bin/ssh-keygen
chmod 740 /system/bin/strace
chmod 000 /system/bin/tcpdump
chmod 740 /system/bin/vim
chmod 000 /system/bin/nano

### This disables Bluetooth (Most users want it on)
###chmod 000 /system/bin/bluetoothd
### ONLY root should need these:
chmod 750 /system/bin/iptables
chmod 750 /system/bin/ping
### Let's remove suid from ping (prevent a Privledge escalation attack)
busybox chmod -s /system/bin/ping
## This is the last step of the hardening
## This will be uncommented only when the system is configured
## This will lockout the system and will only give access through SSH
# Removing unnecessary software
# This must be uncomment at last step
# do a backup before
# Will implement at a later date
#/data/local/removesoftware.sh
#
# disable the Packet Management binary - Prevents installing apps via ADB or remotely
chmod 000 /system/bin/pm
# Disable the adbd daemon
mount -o rw,remount -t rootfs rootfs /
chmod 000 /sbin/adbd # Prevents adb from running. This protects against attacks like P2P-ADB by Kos
mount -o ro,remount -t rootfs rootfs /
