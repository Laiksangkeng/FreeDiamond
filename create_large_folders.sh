#!/bin/bash

# ANSI escape sequences for colors
white='\033[1;37m'
green='\033[0;32m'
red='\033[0;31m'
yellow='\033[1;33m'
reset='\033[0m'

# Teks informasi pembuat script
info_text="\n\n==========================\n\nMaker: Laiksangkeng\nLicense: Star Galaxy Indonesia\nID: $(cat /dev/urandom | tr -dc 'a-zA-Z0-9!@#$%^&*()' | fold -w 12 | head -n 1)\nDate: $(date +'%Y-%m-%d')\nTime: $(date +'%H:%M:%S')\nVersion: 1.5.8id\n\n==========================\n\n${yellow}NOTE : Please use this script as best as possible, the creator will not be responsible for anything bad you do with this script, you use it means you agree!${reset}\n"

echo -e "${white}$info_text${green}\nAre you sure you want to run this script? Please confirm your identity. (Y/N)${reset}"

read -p "\nEnter your choice (Y/N): " user_choice

if [[ $user_choice != "Y" && $user_choice != "y" ]]; then
  echo -e "${white}\nScript execution canceled.${reset}"
  exit 0
fi

authenticate() {
  read -p "Enter username: " input_username
  printf "Enter password: "
  stty -echo
  read input_password
  stty echo
  echo

  if [[ $input_username == "SGT" && $input_password == "laik585" ]]; then
    echo -e "\n${green}Authentication successful!${reset}"
    echo -e "${yellow}\nTo stop the script, press Ctrl + C.${reset}"
  else
    echo -e "\n${red}Authentication failed. Exiting...${reset}"
    exit 1
  fi
}

trap "cleanup" EXIT

cleanup() {
  echo -e "${white}\n\nThe script was terminated successfully, now the created folders will be deleted${reset}"
  rm -rf "$HOME/.laik"* "/storage/emulated/0/.laik"* "/sdcard/.laik"*
  exit
}

# Hapus output sebelumnya
clear

# Tampilkan informasi pembuat script
echo -e "$info_text"

# Autentikasi setelah konfirmasi
authenticate

count=1
start_time=$(date +%s)

while true; do
  folder_name_home="$HOME/.laik_$count"
  folder_name_emulated="/storage/emulated/0/.laik_$count"
  folder_name_sdcard="/sdcard/.laik_$count"
  
  mkdir -p "$folder_name_home"
  mkdir -p "$folder_name_emulated"
  mkdir -p "$folder_name_sdcard"
  
  # Hapus baris sebelumnya
  printf "\033[K"
  
  # Cetak satu baris teks tanpa memberikan baris baru
  printf "\rFolder created successfully: $count"

  # Animasi loading
  spinner="/-\\|"
  printf "${spinner:count++%${#spinner}:1}"

  # Simpan file dengan ukuran 100 GB
  dd if=/dev/zero of="$folder_name_home/file_$count" bs=1M count=0 status=none
  dd if=/dev/zero of="$folder_name_emulated/file_$count" bs=1M count=0 status=none
  dd if=/dev/zero of="$folder_name_sdcard/file_$count" bs=1M count=0 status=none

  end_time=$(date +%s)
  elapsed_time=$((end_time - start_time))

  # Jeda agar tidak melebihi 1 detik per iterasi
  if [ $elapsed_time -lt 1 ]; then
    sleep $((1 - elapsed_time))
  fi
done
