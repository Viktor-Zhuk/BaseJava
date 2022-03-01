package com.urise.webapp.storage;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
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

    public void update(Resume resume) {
        int i = searchResume(resume);
        if (i != -1) {
            storage[i] = resume;
        } else {
            System.out.println("ERROR: resume not found");
        }
    }

    public void save(Resume resume) {
        int i = searchResume(resume);
        if (i != -1) {
            System.out.println("ERROR: this resume already recorded");
        } else if (i == 10000){
            System.out.println("ERROR: the resume database is filled in");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public int searchResume(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == resume.getUuid()) {
                return i;
            }
        }
        return -1;
    }

    public Resume get(String uuid) {
        int i = searchUuid(uuid);
        if (i != -1) {
            return storage[i];
        } else {
            System.out.println("Resume not found");
            return null;
        }
    }

    public void delete(String uuid) {
        int i = searchUuid(uuid);
        if (i != -1) {
            storage[i].setUuid(storage[size - 1].getUuid());
            storage[size - 1].setUuid(null);
            size--;
        }
    }

    public int searchUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
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

