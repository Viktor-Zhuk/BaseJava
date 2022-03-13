package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        String uuidResume = resume.getUuid();
        int index = getIndex(uuidResume);
        if (index != -1) {
            storage[index] = resume;
            System.out.println("Resume " + uuidResume + " successfully updated");
        } else {
            System.out.println("ERROR: resume " + uuidResume + " not found");
        }
    }

    public void save(Resume resume) {
        String uuidResume = resume.getUuid();
        int index = getIndex(uuidResume);
        if (index != -1) {
            System.out.println("ERROR: resume " + uuidResume + " already recorded");
        } else if (size == STORAGE_LIMIT){
            System.out.println("ERROR: the resume database is filled in");
        } else {
            saveResume(resume);
            size++;
        }
    }

    protected abstract void saveResume(Resume resume);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            deleteResume(index);
            size--;
            storage[size] = null;
        } else {
            System.out.println("ERROR: Resume " + uuid + " not deleted");
        }
    }

    protected abstract void deleteResume(int index);

    public Resume[] getAll() {
            return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " not found");
        return null;
    }

    protected abstract int getIndex(String uuid);
}

