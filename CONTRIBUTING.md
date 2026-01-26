# Contributing Guidelines

## Commit Messages

All commits must follow this format:

```
<type>: <short description> (#<issue-number>)
```

### Commit Types

- **feat**: A new feature or functionality
- **fix**: A bug fix
- **doc**: Documentation changes only
- **refactor**: Code restructuring without changing behavior
- **test**: Adding or modifying tests only
- **chore**: Build process, dependencies, or tooling changes

## Commit Rules

1. **Atomic commits**: Every commit is an atomic bug fix or feature, with a clear commit message. The commit contents reflect the commit message. 
2. **Appropriate prefixes**: A prefix convention is appropriate, e.g., commit messages starting with "feat", "fix", "doc", "refactor").
2. **Include tests**: Every bug fix or feature commit contains a test (i.e., adds at least one test or modifies at least one existing test).
3. **Link to issue**: Reference the related issue number in the commit message


## Issues

Before starting work, create or find an existing issue that describes:

- For features: what functionality is being added and why
- For bugs: what the problem is, steps to reproduce, and expected behavior

When creating issues, use clear, descriptive titles.

## Workflow

1. Create or assign yourself to an issue
2. Create a `feature` branch from `main` for your work
3. Make atomic commits following the conventions above
4. Ensure all tests pass
5. Create a pull request referencing the issue
6. Request review from team members
