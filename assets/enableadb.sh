mount -o rw,remount -t rootfs rootfs /
chmod 700 /sbin/adbd # Prevents adb from running. This protects against attacks like P2P-ADB by Kos
mount -o ro,remount -t rootfs rootfs /
