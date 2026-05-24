# HIST: Policy Decision Point
### Holistic Identity Security & Trust

## Overview

HIST is a Policy Decision Point (PDP) application designed to enforce zero trust architecture principles per NIST special publication standards. The application accepts bearer tokens from CB Authorization Hub (CBOAuth) and evaluates them against configurable policies combined with real-time User Entity Behavior Analysis (UEBA) data.


## Intent

This PDP application serves the following core purposes:

- **Token Acceptance**: Accept and process bearer tokens from the CB Authorization Hub (CBOAuth)
- **Policy Enforcement**: Apply configured policies to the identity or identities represented in the bearer token
- **Behavioral Analysis**: Analyze real-time User Entity Behavior Analysis (UEBA) data alongside token data to make access decisions
- **Zero Trust Implementation**: Enforce zero trust architecture principles as defined by NIST standards, ensuring continuous verification and least-privilege access
- **Access Decision Making**: Determine whether access will be granted or denied based on the combination of token data and behavioral insights
