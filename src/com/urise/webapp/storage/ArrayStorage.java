package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        String uuidResume = resume.getUuid();
        int index = findIndex(uuidResume);
        if (index != -1) {
            storage[index] = resume;
            System.out.println("Resume " + uuidResume + " successfully updated");
        } else {
            System.out.println("ERROR: resume " + uuidResume + " not found");
        }
    }

    public void save(Resume resume) {
        String uuidResume = resume.getUuid();
        int index = findIndex(uuidResume);
        if (index != -1) {
            System.out.println("ERROR: resume " + uuidResume + " already recorded");
        } else if (index == storage.length){
            System.out.println("ERROR: the resume database is filled in");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " not found");
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: Resume " + uuid + " not deleted");
        }
    }

    public int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            String uuidInBase = storage[i].getUuid();
            if (uuidInBase.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}

