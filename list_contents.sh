#!/bin/bash

# Define the root directory to start from, default is current directory
root_dir=${1:-.}
output_file="directory_structure_and_java_contents.txt"

# Function to print the hierarchy
print_hierarchy() {
    local dir=$1
    for file in "$dir"/*; do
        if [ -d "$file" ]; then
            echo "Directory: $file" >> "$output_file"
            print_hierarchy "$file"
        elif [ -f "$file" ]; then
            echo "File: $file" >> "$output_file"
        fi
    done
}

# Clear or create the output file
> "$output_file"

# Print the hierarchy
print_hierarchy "$root_dir"

# Find and append content of Java files to the output file
find "$root_dir" -name "*.java" -exec sh -c 'echo -e "\n$1:\n$(cat "$1")"' _ {} \; >> "$output_file"
