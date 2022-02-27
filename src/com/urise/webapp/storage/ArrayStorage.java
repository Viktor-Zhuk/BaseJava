package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;


    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume resume) {
        boolean isNewResume = true;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == resume.uuid) {
                isNewResume = false;
                break;
            }
        }
        if (isNewResume) {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        System.out.println("com.urise.webapp.model.Resume not found");
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                storage[i].uuid = storage[size - 1].uuid;
                storage[size - 1].uuid = null;
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] filledResume = new Resume[size];
        for (int i = 0; i < size; i++) {
            filledResume[i] = storage[i];
        }
        return filledResume;
    }

    public int size() {
        return size;
    }
}
